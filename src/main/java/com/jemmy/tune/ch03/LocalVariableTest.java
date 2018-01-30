/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: LocalVariableTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/18 15:35
 * Description: 
 */
package com.jemmy.tune.ch03;

/**
 * <pre>
 * 3.5.2 使用局部变量
 *
 * @author Cheng Zhujiang
 * @date 2017/12/18
 */
public class LocalVariableTest {

    public static int ta = 0; // 在类中定义变量

    public static void main(String[] args) {
        long start = System.nanoTime();
        testLocalVar();
        long end1 = System.nanoTime();
        System.out.println("testLocalVar cost: " + (end1 - start)); // 2952310 ns
        testStaticVar();
        long end2 = System.nanoTime();
        System.out.println("testStaticVar cost: " + (end2 - end1)); // 18816961 ns
    }

    private static void testLocalVar() {
        int a = 0; // 在函数体内，定义局部变量
        for (int i = 0; i < 100000000; i++) {
            a++;
        }
    }

    private static void testStaticVar() {
        for (int i = 0; i < 100000000; i++) {
            ta++; // 函数体中，调用类的static变量
        }
    }
}
