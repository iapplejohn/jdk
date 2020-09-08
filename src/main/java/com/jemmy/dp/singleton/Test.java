/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: SerializeTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/13 13:55
 * Description: 
 */
package com.jemmy.dp.singleton;

/**
 * <pre>
 * 测试急加载单例和懒加载加锁单例的耗时
 *
 * @author Cheng Zhujiang
 * @date 2017/9/13
 */
public class Test {

    static class Task implements Runnable {

        @Override
        public void run() {
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
//                Singleton.getInstance();
                LazySingleton.getInstance();
            }
            System.out.println("Spend:" + (System.currentTimeMillis() - beginTime));
        }
    }

    public static void main(String[] args) {
        for (int j = 0; j < 5; j++) {
            new Thread(new Task()).start();
        }
    }
}
