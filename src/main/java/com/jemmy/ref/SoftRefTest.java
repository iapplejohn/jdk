/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: SoftRefTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/13 15:02
 * Description: 
 */
package com.jemmy.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * SoftRefTest
 * -Xmx5m
 *
 * @author Cheng Zhujiang
 * @date 2017/8/13
 */
public class SoftRefTest {

    public static void main(String[] args) {
        MyObject obj = new MyObject(); // 强引用
        ReferenceQueue<MyObject> softQueue = new ReferenceQueue<>(); // 创建引用队列
        SoftReference<MyObject> softRef = new SoftReference<>(obj, softQueue);
        CheckRefQueueThread check = new CheckRefQueueThread();
        check.setRefQueue(softQueue);
        check.start(); // 检查引用队列，监控对象回收情况
        obj = null;
        System.gc();
        System.out.println("After GC:Soft Get= " + softRef.get());
        System.out.println("分配大块内存");
        byte[] b = new byte[4 * 1024 * 870]; // 分配一块较大内存区，强迫GC 这里大小很重要,写成900就OOM
        System.gc(); // 显式触发gc
        System.out.println("After new byte[]: Soft Get= " + softRef.get());
    }
}
