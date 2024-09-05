package com.jemmy.spi;

import com.jemmy.spi.annotation.Spi;

/**
 * @author zhujiang.cheng
 * @since 2023/5/22
 */
@Spi
public interface ExtFactory {

    /**
     * Get extension.
     *
     * @param type object type.
     * @param name object name.
     * @return object instance.
     */
    <T> T getExtension(Class<T> type, String name);
}
