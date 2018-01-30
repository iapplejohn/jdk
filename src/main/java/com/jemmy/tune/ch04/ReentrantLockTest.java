/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: ReentrantLockTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/18 7:41
 * Description: 
 */
package com.jemmy.tune.ch04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * ReentrantLock.lock(), .tryLock(), .tryLock(timeout, timeUnit)在执行时都不响应中断。
 * 执行完后，发现被中断，则重新执行业务逻辑。
 *
 * @author Cheng Zhujiang
 * @date 2017/8/18
 */
public class ReentrantLockTest {

    private static final ReentrantLock lock = new ReentrantLock();

    private static Runnable createTask() {
        return new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
//                        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
//                        if (lock.tryLock()) {
//                        lock.lock();
                        lock.lockInterruptibly();
                            try {
                                System.out.println("locked " + Thread.currentThread().getName());
                                TimeUnit.MILLISECONDS.sleep(1000);
                            } finally {
                                lock.unlock();
                                System.out.println("unlocked " + Thread.currentThread().getName());
                            }
                            break;
//                        } else {
//                            System.out.println("unable to lock " + Thread.currentThread().getName());
//                        }
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " is interrupted");
                    }
                }
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(createTask(), "FirstThread");
        Thread second = new Thread(createTask(), "SecondThread");
        first.start();
        second.start();
        TimeUnit.MILLISECONDS.sleep(600);
        second.interrupt(); // 中断
    }
}
