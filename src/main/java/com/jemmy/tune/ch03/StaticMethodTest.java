/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: StaticMethodTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/18 19:21
 * Description: 
 */
package com.jemmy.tune.ch03;

import org.junit.Test;

/**
 * <pre>
 * 静态方法替代实例方法
 *
 * @author Cheng Zhujiang
 * @date 2017/12/18
 */
public class StaticMethodTest {

    public static void staticMethod() {

    }

    public void instanceMethod() {

    }

    @Test
    public void test() {
        int CIRCLE = 100000000;
        long start = System.nanoTime();
        for (int i = 0; i < CIRCLE; i++) {
            staticMethod();
        }
        long end1 = System.nanoTime();
        System.out.println("staticMethod cost: " + (end1 - start)); // 7727948 ns
        for (int i = 0; i < CIRCLE; i++) {
            instanceMethod();
        }
        long end2 = System.nanoTime();
        System.out.println("instanceMethod cost: " + (end2 - end1)); // 10663748 ns
    }
}
