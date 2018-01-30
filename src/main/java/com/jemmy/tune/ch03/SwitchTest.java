/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: SwitchTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/18 17:21
 * Description: 
 */
package com.jemmy.tune.ch03;

/**
 * <pre>
 * 3.5.4 替换switch,结果不然
 *
 * @author Cheng Zhujiang
 * @date 2017/12/18
 */
public class SwitchTest {

    public static void main(String[] args) {
        long start = System.nanoTime();
        testSwitch();
        long end1 = System.nanoTime();
        System.out.println("testSwitch cost: " + (end1 - start)); // 7051452 ns
        testArray();
        long end2 = System.nanoTime();
        System.out.println("testArray cost: " + (end2 - end1)); // 53308474 ns
    }

    private static void testSwitch() {
        int re = 0;
        for (int i = 0; i < 10000000; i++) {
            re = switchInt(i);
        }
    }

    private static int switchInt(int z) {
        int i = z % 10 + 1;
        switch (i) {
            case 1: return 3;
            case 2: return 6;
            case 3: return 7;
            case 4: return 8;
            case 5: return 10;
            case 6: return 16;
            case 7: return 18;
            case 8: return 44;
            default: return -1;
        }
    }

    private static void testArray() {
        int re = 0;
        int[] sw = new int[] { 0, 3, 6, 7, 8, 10, 16, 18, 44 };
        for (int i = 0; i < 10000000; i++) {
            re = arrayInt(sw, i);
        }
    }

    private static int arrayInt(int[] sw, int z) {
        int i = z % 10 + 1;
        if (i > 7 || i < 1) { // 模拟switch的default
            return -1;
        } else {
            return sw[i];
        }
    }


}
