package com.jemmy.spi.test;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static java.util.ServiceLoader.load;
import static java.util.stream.StreamSupport.stream;

import com.jemmy.spi.factory.AdaptiveExtFactory;
import com.jemmy.spi.util.ArrayUtils;
import com.jemmy.spi.util.CollectionUtils;
import com.jemmy.spi.ConcurrentHashSet;
import com.jemmy.spi.ExtFactory;
import com.jemmy.spi.ExtLoader;
import com.jemmy.spi.Holder;
import com.jemmy.spi.annotation.Inject;
import com.jemmy.spi.annotation.Inject.InjectType;
import com.jemmy.spi.Lifecycle;
import com.jemmy.spi.LoadingStrategy;
import com.jemmy.spi.util.ReflectUtils;
import com.jemmy.spi.annotation.Spi;
import com.jemmy.spi.annotation.SpiWrapper;
import com.jemmy.spi.support.WrapperComparator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.apache.dubbo.common.lang.Prioritized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author zhujiang.cheng
 * @since 2023/6/2
 */
public class SimpleExtLoader<T> {

    private static final Logger log = LoggerFactory.getLogger(ExtLoader.class);

    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

    private final Class<T> type;

    private final ExtFactory objectFactory;

    private static final ConcurrentMap<Class<?>, ExtLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<>(64);

    private static final ConcurrentMap<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<>(64);

    private final ConcurrentMap<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<>();

    private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();

    private Set<Class<?>> cachedWrapperClasses;

    private String cachedDefaultName;

    private final static String dir = "META-INF/arch/";

    private final Map<String, IllegalStateException> exceptions = new ConcurrentHashMap<>();

    /**
     * Record all unacceptable exceptions when using SPI
     */
    private Set<String> unacceptableExceptions = new ConcurrentHashSet<>();

    private static volatile LoadingStrategy[] strategies = loadLoadingStrategies();

    private static LoadingStrategy[] loadLoadingStrategies() {
        return stream(load(LoadingStrategy.class).spliterator(), false)
            .sorted()
            .toArray(LoadingStrategy[]::new);
    }

    public static List<LoadingStrategy> getLoadingStrategies() {
        return asList(strategies);
    }

    public SimpleExtLoader(Class<T> type) {
        this.type = type;
        objectFactory = new AdaptiveExtFactory();
    }

    private static <T> boolean withExtensionAnnotation(Class<T> type) {
        return type.isAnnotationPresent(Spi.class);
    }

    public static <T> ExtLoader<T> getExtensionLoader(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Extension type == null");
        }
        if (!type.isInterface()) {
            throw new IllegalArgumentException("Extension type (" + type + ") is not an interface!");
        }
        if (!withExtensionAnnotation(type)) {
            throw new IllegalArgumentException("Extension type (" + type +
                ") is not an extension, because it is NOT annotated with @" + Spi.class.getSimpleName() + "!");
        }

        ExtLoader<T> loader = (ExtLoader<T>) EXTENSION_LOADERS.get(type);
        if (loader == null) {
            EXTENSION_LOADERS.putIfAbsent(type, new ExtLoader<T>(type));
            loader = (ExtLoader<T>) EXTENSION_LOADERS.get(type);
        }
        return loader;
    }

    private Map<String, Class<?>> loadExtensionClasses() {
        try {
            // 存储结果
            Map<String, Class<?>> extensionClasses = new HashMap<>();
            // 扩展文件名称
            String fileName = dir + type.getName();
            // JDK 类加载器
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // 加载配置文件
            Enumeration<URL> urls = classLoader.getResources(fileName);

            if (urls != null) {
                while (urls.hasMoreElements()) {
                    URL resourceURL = urls.nextElement();
                    loadResource(extensionClasses, classLoader, resourceURL);
                }
            }
            return extensionClasses;
        } catch (Throwable e) {
            throw new IllegalStateException();
        }
    }

    /**
     * extract and cache default extension name if exists
     */
    private void cacheDefaultExtensionName() {
        final Spi defaultAnnotation = type.getAnnotation(Spi.class);
        if (defaultAnnotation == null) {
            return;
        }

        String value = defaultAnnotation.value();
        if ((value = value.trim()).length() > 0) {
            String[] names = NAME_SEPARATOR.split(value);
            if (names.length > 1) {
                throw new IllegalStateException("More than 1 default extension name on extension " + type.getName()
                    + ": " + Arrays.toString(names));
            }
            if (names.length == 1) {
                cachedDefaultName = names[0];
            }
        }
    }

    private void loadResource(Map<String, Class<?>> extensionClasses, ClassLoader classLoader, URL resourceURL) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceURL.openStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    // 找到等号
                    int i = line.indexOf('=');
                    // name
                    String simpleName = line.substring(0, i).trim();
                    // 实现类全限定名
                    String name = line.substring(i + 1).trim();
                    extensionClasses.put(simpleName, Class.forName(name, true, classLoader));
                }
            }

        } catch (Throwable t) {
            throw new IllegalStateException();
        }
    }

    /**
     * Return default extension, return <code>null</code> if it's not configured.
     */
    public T getDefaultExtension() {
        getExtensionClasses();
        if (org.apache.dubbo.common.utils.StringUtils.isBlank(cachedDefaultName) || "true".equals(cachedDefaultName)) {
            return null;
        }
        return getExtension(cachedDefaultName);
    }

    public T getExtension(String name) {
        return getExtension(name, true);
    }

    public T getExtension(String name, boolean wrap) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Extension name == null");
        }
        if ("true".equals(name)) {
            return getDefaultExtension();
        }
        final Holder<Object> holder = getOrCreateHolder(name);
        Object instance = holder.get();
        if (instance == null) {
            synchronized (holder) {
                instance = holder.get();
                if (instance == null) {
                    instance = createExtension(name, wrap);
                    holder.set(instance);
                }
            }
        }
        return (T) instance;
    }

    private IllegalStateException findException(String name) {
        StringBuilder buf = new StringBuilder("No such extension " + type.getName() + " by name " + name);

        int i = 1;
        for (Map.Entry<String, IllegalStateException> entry : exceptions.entrySet()) {
            if (entry.getKey().toLowerCase().startsWith(name.toLowerCase())) {
                if (i == 1) {
                    buf.append(", possible causes: ");
                }
                buf.append("\r\n(");
                buf.append(i++);
                buf.append(") ");
                buf.append(entry.getKey());
                buf.append(":\r\n");
                buf.append(org.apache.dubbo.common.utils.StringUtils.toString(entry.getValue()));
            }
        }

        if (i == 1) {
            buf.append(", no related exception was found, please check whether related SPI module is missing.");
        }
        return new IllegalStateException(buf.toString());
    }

    private T createExtension(String name, boolean wrap) {
        Class<?> clazz = getExtensionClasses().get(name);
        if (clazz == null || unacceptableExceptions.contains(name)) {
            throw findException(name);
        }

        try {
            T instance = (T) EXTENSION_INSTANCES.get(clazz);
            if (instance == null) {
                EXTENSION_INSTANCES.putIfAbsent(clazz, clazz.getDeclaredConstructor().newInstance());
                instance = (T) EXTENSION_INSTANCES.get(clazz);
            }
            injectExtension(instance);

            List<Class<?>> wrapperClassesList = new ArrayList<>();
            if (cachedWrapperClasses != null) {
                wrapperClassesList.addAll(cachedWrapperClasses);
                wrapperClassesList.sort(WrapperComparator.COMPARATOR);
                Collections.reverse(wrapperClassesList);
            }

            if (CollectionUtils.isNotEmpty(wrapperClassesList)) {
                for (Class<?> wrapperClass : wrapperClassesList) {
                    SpiWrapper wrapper = wrapperClass.getAnnotation(SpiWrapper.class);
                    if (wrapper == null || (ArrayUtils.contains(wrapper.matches(), name) && !ArrayUtils.contains(wrapper.mismatches(), name))) {
                        instance = injectExtension((T) wrapperClass.getConstructor(type).newInstance(instance));
                    }
                }
            }

            initExtension(instance);
            return instance;
        } catch (Throwable t) {
            throw new IllegalStateException("Extension instance (name: " + name + ", class: " +
                type + ") couldn't be instantiated: " + t.getMessage(), t);
        }
    }

    private T injectExtension(T instance) {
        if (objectFactory == null) {
            return instance;
        }

        try {
            for (Method method : instance.getClass().getMethods()) {
                if (!isSetter(method)) {
                    continue;
                }

                Class<?> pt = method.getParameterTypes()[0];
                if (ReflectUtils.isPrimitive(pt)) {
                    continue;
                }

                String property = getSetterProperty(method);
                Inject inject = method.getAnnotation(Inject.class);
                if (inject == null) {
                    injectValue(instance, method, pt, property);
                } else {
                    if (!inject.enable()) {
                        continue;
                    }

                    if (inject.type() == InjectType.ByType) {
                        injectValue(instance, method, pt, null);
                    } else {
                        injectValue(instance, method, pt, property);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return instance;
    }

    private void injectValue(T instance, Method method, Class<?> pt, String property) {
        try {
            Object object = objectFactory.getExtension(pt, property);
            if (object != null) {
                method.invoke(instance, object);
            }
        } catch (Exception e) {
            log.error("Failed to inject via method " + method.getName()
                + " of interface " + type.getName() + ": " + e.getMessage(), e);
        }
    }

    public Set<String> getSupportedExtensions() {
        Map<String, Class<?>> classes = getExtensionClasses();
        return Collections.unmodifiableSet(new TreeSet<>(classes.keySet()));
    }

    public Set<T> getSupportedExtensionInstances() {
        List<T> instances = new LinkedList<>();
        Set<String> supportedExtensions = getSupportedExtensions();
        if (CollectionUtils.isNotEmpty(supportedExtensions)) {
            for (String name : supportedExtensions) {
                instances.add(getExtension(name));
            }
        }
        // sort the Prioritized instances
        sort(instances, Prioritized.COMPARATOR);
        return new LinkedHashSet<>(instances);
    }

    private Map<String, Class<?>> getExtensionClasses() {
        Map<String, Class<?>> classes = cachedClasses.get();
        if (classes == null) {
            synchronized (cachedClasses) {
                classes = cachedClasses.get();
                if (classes == null) {
                    classes = loadExtensionClasses();
                    cachedClasses.set(classes);
                }
            }
        }

        return classes;
    }

    private Holder<Object> getOrCreateHolder(String name) {
        Holder<Object> holder = cachedInstances.get(name);
        if (holder == null) {
            cachedInstances.putIfAbsent(name, new Holder<>());
            holder = cachedInstances.get(name);
        }
        return holder;
    }

//    /**
//     * 根据name获取实现实例
//     */
//    public T getExtension(String name) {
//        Class<?> clazz = loadExtensionClasses().get(name);
//        try {
//            return (T) clazz.newInstance();
//        } catch (Exception e) {
//            throw new IllegalStateException();
//        }
//    }

    private boolean isSetter(Method method) {
        return method.getName().startsWith("set")
            && method.getParameterTypes().length == 1
            && Modifier.isPublic(method.getModifiers());
    }

    private void initExtension(T instance) {
        if (instance instanceof Lifecycle) {
            Lifecycle lifecycle = (Lifecycle) instance;
            lifecycle.initialize();
        }
    }

    private String getSetterProperty(Method method) {
        return method.getName().length() > 3 ? method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4) : "";
    }
}
