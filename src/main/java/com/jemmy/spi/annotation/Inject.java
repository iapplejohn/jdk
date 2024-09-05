package com.jemmy.spi.annotation;

import static com.jemmy.spi.annotation.Inject.InjectType.ByName;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhujiang.cheng
 * @since 2023/5/22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Inject {

    // whether enable injection or not
    boolean enable() default true;

    // inject type default by name injection
    InjectType type() default ByName;

    enum InjectType{
        ByName,
        ByType
    }
}
