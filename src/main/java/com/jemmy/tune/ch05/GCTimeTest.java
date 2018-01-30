/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: GCTimeTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/16 8:49
 * Description: 
 */
package com.jemmy.tune.ch05;

import java.util.HashMap;

/**
 * <pre>
 * GCTimeTest
 *
 * @author Cheng Zhujiang
 * @date 2017/8/16
 */
public class GCTimeTest {

    static HashMap map = new HashMap();

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            if (map.size() * 512 / 1024 / 1024 >= 400) { // 保护内存不溢出
                map.clear();
                System.out.println("clean map");
            }
            byte[] b1;
            for (int j = 0; j < 100; j++) { // 模拟对内存的消耗
                b1 = new byte[512];
                map.put(System.nanoTime(), b1);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }
}
