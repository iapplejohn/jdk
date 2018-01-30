/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: ReadWriteLockTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/18 8:32
 * Description: 
 */
package com.jemmy.tune.ch04;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 * ReadWriteLockTest
 *
 * @author Cheng Zhujiang
 * @date 2017/8/18
 */
public class ReadWriteLockTest {

    private Object value;

    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    public Object handleRead() throws InterruptedException {
        try {
            lock.lock(); // 模拟读操作
            TimeUnit.MILLISECONDS.sleep(1);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(int index) throws InterruptedException {
        try {
            lock.lock();
            TimeUnit.MILLISECONDS.sleep(1);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public Object handleRead2() throws InterruptedException {
        try {
            readLock.lock();
            TimeUnit.MILLISECONDS.sleep(1);
            return value;
        } finally {
            readLock.unlock();
        }
    }

    public void handleWrite2(int index) throws InterruptedException {
        try {
            writeLock.lock();
            TimeUnit.MILLISECONDS.sleep(1);
            value = index;
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        CountDownLatch latch = new CountDownLatch(200);
        ReadWriteLockTest test = new ReadWriteLockTest();
        for (int i = 0; i < 200; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        test.handleRead();
                        test.handleWrite(100);
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        latch.await();
        long end1 = System.nanoTime();
        System.out.printf("The 1st costs %d ns\n", (end1 - start));

        CountDownLatch latch2 = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        test.handleRead2();
                        test.handleWrite2(500);
                        latch2.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        latch.await();
        long end2 = System.nanoTime();
        System.out.printf("The 2nd costs %d ns\n", (end2 - end1));
    }

}
