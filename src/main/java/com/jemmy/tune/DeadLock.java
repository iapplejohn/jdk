/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: DeadLock.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/8 8:57
 * Description: 
 */
package com.jemmy.tune;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DeadLock
 *
 * @author Cheng Zhujiang
 * @date 2017/8/8
 */
public class DeadLock extends Thread {

    protected Object myDirect;

    static ReentrantLock south = new ReentrantLock();
    static ReentrantLock north = new ReentrantLock();

    public DeadLock(Object obj) {
        this.myDirect = obj;
        if (myDirect == south) {
            this.setName("south");
        }
        if (myDirect == north) {
            this.setName("north");
        }
    }

    @Override
    public void run() {
        if (myDirect == south) {
            try {
                north.lockInterruptibly(); // 占用north
                try {
                    TimeUnit.MILLISECONDS.sleep(500); // 等待north启动
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                south.lockInterruptibly(); // 占用south
                System.out.println("car to south has passed");
            } catch (InterruptedException e) {
                System.out.println("car to south is killed");
            } finally {
                if (north.isHeldByCurrentThread()) {
                    north.unlock();
                }
                if (south.isHeldByCurrentThread()) {
                    south.unlock();
                }
            }
        }
        if (myDirect == north) {
            try {
                south.lockInterruptibly(); // 占用south
                try {
                    TimeUnit.MILLISECONDS.sleep(500); // 等待south启动
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                north.lockInterruptibly(); // 占用north
                System.out.println("car to north has passed");
            } catch (InterruptedException e) {
                System.out.println("car to north is killed");
            } finally {
                if (north.isHeldByCurrentThread()) {
                    north.unlock();
                }
                if (south.isHeldByCurrentThread()) {
                    south.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock car2south = new DeadLock(south); // 2个线程死锁
        DeadLock car2north = new DeadLock(north);
        car2south.start();
        car2north.start();
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
