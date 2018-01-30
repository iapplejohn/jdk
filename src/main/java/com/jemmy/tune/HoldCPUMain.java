/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: HoldCPUMain.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/2 22:18
 * Description: 
 */
package com.jemmy.tune;

import java.util.concurrent.TimeUnit;

/**
 * HoldCPUMain
 *
 * @author Cheng Zhujiang
 * @date 2017/8/2
 */
public class HoldCPUMain {

    public static class HoldCPUTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                double a = Math.random() * Math.random(); // 占用CPU
            }
        }
    }

    public static class LazyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    TimeUnit.MILLISECONDS.sleep(1000); // 空闲线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new HoldCPUTask()).start(); // 开启线程，占用CPU
        new Thread(new LazyTask()).start(); // 空闲线程
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }
}
