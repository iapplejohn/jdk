/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: StopWorldTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/16 8:50
 * Description: 
 */
package com.jemmy.tune.ch05;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * StopWorldTest
 *
 * @author Cheng Zhujiang
 * @date 2017/8/16
 */
public class StopWorldTest {

    public static class MyThread extends Thread {
        HashMap<Long, byte[]> map = new HashMap<>();

        @Override
        public void run() {
            try {
                while (true) {
                    if (map.size() * 512 / 1024 / 1024 >= 400) { // 防止内存溢出
                        map.clear();
                        System.out.println("clean map");
                    }
                    byte[] bytes;
                    for (int i = 0; i < 100; i++) {
                        bytes = new byte[1024]; // 模拟内存占用
                        map.put(System.nanoTime(), bytes);
                    }
                    TimeUnit.MILLISECONDS.sleep(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 每毫秒打印时间信息
    public static class PrintThread extends Thread {
        public static final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        PrintThread p = new PrintThread();
        t.start();
        p.start();
    }
}
