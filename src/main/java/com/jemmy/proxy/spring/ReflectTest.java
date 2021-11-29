package com.jemmy.proxy.spring;

import java.lang.reflect.Method;

/**
 * NoSuchMethodException 是在反射的时候，找不到指定名称和参数的方法而抛出的异常
 *
 * @author zhujiang.cheng
 * @since 2021/6/21
 */
public class ReflectTest {

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = A.class.getDeclaredMethod("fetch", String.class, TestImpl.class);
        System.out.println("method = " + method);
    }
}
