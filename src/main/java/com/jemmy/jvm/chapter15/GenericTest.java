package com.jemmy.jvm.chapter15;

/**
 * 深入拆解Java虚拟机 - 泛型擦除
 *
 * @author zhujiang.cheng
 * @since 2020/5/27
 */
public class GenericTest<T extends Number> {

    T foo(T t) {
        return t;
    }

}
