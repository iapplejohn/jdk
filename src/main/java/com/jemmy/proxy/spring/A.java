package com.jemmy.proxy.spring;

/**
 * @author zhujiang.cheng
 * @since 2021/6/9
 */
public class A {

    public A() {
    }

    public Object fetch(String str, TestInterface testInterface) {
        System.out.println("str = " + str);
        System.out.println("testInterface = " + testInterface);
        return "good";
    }
}
