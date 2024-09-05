package com.jemmy.spi;

import com.jemmy.spi.lang.Prioritized;

/**
 * @author zhujiang.cheng
 * @since 2023/6/1
 */
public interface LoadingStrategy extends Prioritized {

    String directory();

    default boolean preferExtensionClassLoader() {
        return false;
    }

    default String[] excludedPackages() {
        return null;
    }

    default boolean overridden() {
        return false;
    }
}
