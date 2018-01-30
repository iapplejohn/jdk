/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: BitOperationTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/18 15:53
 * Description: 
 */
package com.jemmy.tune.ch03;

/**
 * <pre>
 * 3.5.3 位运算代替乘除法
 *
 * @author Cheng Zhujiang
 * @date 2017/12/18
 */
public class BitOperationTest {

    public static void main(String[] args) {
        long start = System.nanoTime();
        testArithMeticOperation();
        long end1 = System.nanoTime();
        System.out.println("testArithMeticOperation cost: " + (end1 - start)); // 8946624 ns
        testBitOperation();
        long end2 = System.nanoTime();
        System.out.println("testBitOperation cost: " + (end2 - end1)); // 6825655 ns
    }

    private static void testArithMeticOperation() {
        long a = 100;
        for (int i = 0; i < 100000000; i++) {
            a *= 2;
            a /= 2;
        }
    }

    private static void testBitOperation() {
        long a = 100;
        for (int i = 0; i < 100000000; i++) {
            a <<= 1; // 乘以2
            a >>= 1; // 除以2
        }
    }
}
