package com.jemmy.spi.util;

import java.util.Date;

/**
 * @author zhujiang.cheng
 * @since 2023/5/22
 */
public class ReflectUtils {

    public static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Boolean.class || cls == Character.class
            || Number.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls);
    }
}
