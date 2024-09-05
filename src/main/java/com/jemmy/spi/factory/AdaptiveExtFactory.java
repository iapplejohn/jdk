package com.jemmy.spi.factory;

import com.jemmy.spi.ExtFactory;
import com.jemmy.spi.ExtLoader;
import edu.emory.mathcs.backport.java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiang.cheng
 * @since 2023/5/22
 */
public class AdaptiveExtFactory implements ExtFactory {

    private final List<ExtFactory> factories;

    public AdaptiveExtFactory() {
        ExtLoader<ExtFactory> extLoader = ExtLoader.getExtensionLoader(ExtFactory.class);
        List<ExtFactory> list = new ArrayList<>();
        for (String name : extLoader.getSupportedExtensions()) {
            list.add(extLoader.getExtension(name));
        }
        this.factories = Collections.unmodifiableList(list);
    }

    @Override
    public <T> T getExtension(Class<T> type, String name) {
        for (ExtFactory factory : factories) {
            T extension = factory.getExtension(type, name);
            if (extension != null) {
                return extension;
            }
        }
        return null;
    }
}
