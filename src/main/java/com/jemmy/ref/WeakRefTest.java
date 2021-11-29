/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: WeakRefTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/13 16:00
 * Description: 
 */
package com.jemmy.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * WeakRefTest
 *
 * @author Cheng Zhujiang
 * @date 2017/8/13
 */
public class WeakRefTest {

    public static void main(String[] args) {
        MyObject obj = new MyObject();
        ReferenceQueue<MyObject> weakQueue = new ReferenceQueue<>();
        WeakReference<MyObject> weakRef = new WeakReference<MyObject>(obj, weakQueue);
        CheckRefQueueThread check = new CheckRefQueueThread();
        check.setRefQueue(weakQueue);
        check.start();
        obj = null;
        System.out.println("Before GC: Weak Get= " + weakRef.get());
        System.gc();
        System.out.println("After GC: Weak Get= " + weakRef.get());
    }

}
