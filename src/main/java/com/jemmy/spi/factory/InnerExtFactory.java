package com.jemmy.spi.factory;

import com.jemmy.spi.ExtFactory;
import com.jemmy.spi.ExtLoader;
import com.jemmy.spi.annotation.Spi;
import com.jemmy.spi.annotation.SpiActivate;

/**
 * @author zhujiang.cheng
 * @since 2023/6/3
 */
@SpiActivate
public class InnerExtFactory implements ExtFactory {

    @Override
    public <T> T getExtension(Class<T> type, String name) {
        if (type.isInterface() && type.isAnnotationPresent(Spi.class)) {
            ExtLoader<T> loader = ExtLoader.getExtensionLoader(type);
            if (!loader.getSupportedExtensions().isEmpty()) {
                return loader.getDefaultExtension();
            }
        }
        return null;
    }
}
