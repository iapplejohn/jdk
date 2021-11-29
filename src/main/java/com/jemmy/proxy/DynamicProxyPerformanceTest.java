package com.jemmy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author zhujiang.cheng
 * @since 2021/3/7
 */
public class DynamicProxyPerformanceTest {

    public static void main(String[] args) throws Exception {
        CountService delegate = new CountServiceImpl();

        long time = System.currentTimeMillis();
        CountService jdkProxy = createJdkDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.println("Create JDK Proxy: " + time + " ms");

        time = System.currentTimeMillis();
        CountService cglibProxy = createCglibDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.println("Create CGLIB Proxy: " + time + " ms");

        time = System.currentTimeMillis();
        CountService javassistProxy = createJavassistDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.println("Create JAVAASSIST Proxy: " + time + " ms");

        time = System.currentTimeMillis();
        CountService javassistBytecodeProxy = createJavassistBytecodeDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.println("Create JAVAASSIST Bytecode Proxy: " + time + " ms");
    }

    private static CountService createJdkDynamicProxy(CountService delegate) {
        CountService jdkProxy = (CountService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
            new Class[]{CountService.class}, new JdkHandler(delegate));
        return jdkProxy;
    }

    private static class JdkHandler implements InvocationHandler {

        final Object delegate;

        JdkHandler(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(delegate, args);
        }
    }

    private static CountService createCglibDynamicProxy(CountService delegate) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibInterceptor(delegate));
        enhancer.setInterfaces(new Class[]{CountService.class});
        CountService cglibProxy = (CountService) enhancer.create();
        return cglibProxy;
    }

    private static class CglibInterceptor implements MethodInterceptor {

        final Object delegate;

        CglibInterceptor(CountService delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return methodProxy.invoke(delegate, objects);
        }
    }

    private static CountService createJavassistDynamicProxy(CountService delegate)
        throws IllegalAccessException, InstantiationException {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[]{CountService.class});
        Class<?> proxyClass = proxyFactory.createClass();
        CountService javassistProxy = (CountService) proxyClass.newInstance();
        ((ProxyObject)javassistProxy).setHandler(new JavaAssistInterceptor(delegate));
        // TODO
        return null;
    }

    private static class JavaAssistInterceptor implements MethodHandler {

        final Object delegate;

        public JavaAssistInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
            return method.invoke(delegate, objects);
        }
    }

    private static CountService createJavassistBytecodeDynamicProxy(CountService delegate) throws NotFoundException {
        ClassPool mPool = new ClassPool(true);
        CtClass mCtc = mPool.makeClass(CountService.class.getName() + "JavassistProxy");
        mCtc.addInterface(mPool.get(CountService.class.getName()));
        // TODO
        return null;
    }
}
