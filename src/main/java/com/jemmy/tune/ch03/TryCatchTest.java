/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: TryCatchTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/18 15:27
 * Description: 
 */
package com.jemmy.tune.ch03;

/**
 * <pre>
 * 3.5.1 慎用异常
 *
 * @author Cheng Zhujiang
 * @date 2017/12/18
 */
public class TryCatchTest {

    public static void main(String[] args) {
        long start = System.nanoTime();
        tryInsideLoop();
        long end1 = System.nanoTime();
        System.out.println("tryInsideLoop cost: " + (end1 - start)); // 4812676 ns
        tryOutsideLoop();
        long end2 = System.nanoTime();
        System.out.println("tryOutsideLoop cost: " + (end2 - end1)); // 4354836 ns
    }

    private static void tryInsideLoop() {
        int a = 0;
        for (int i = 0; i < 100000000; i++) {
            try { // 在循环体内
                a++;
            } catch (Exception e) {

            }
        }
    }

    private static void tryOutsideLoop() {
        int a = 0;
        try { // 在循环体内
            for (int i = 0; i < 100000000; i++) {
                a++;
            }
        } catch (Exception e) {

        }
    }
}
