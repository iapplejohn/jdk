/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: CheckRefQueueThread.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/13 15:09
 * Description: 
 */
package com.jemmy.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * CheckRefQueueThread
 *
 * @author Cheng Zhujiang
 * @date 2017/8/13
 */
public class CheckRefQueueThread extends Thread {

    ReferenceQueue<MyObject> refQueue;

    @Override
    public void run() {
        Reference<MyObject> obj = null;

        try {
            obj = (Reference<MyObject>) refQueue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (obj != null) {
            System.out.println("Object for Reference is " + obj.get());
        }
    }

    public void setRefQueue(ReferenceQueue<MyObject> refQueue) {
        this.refQueue = refQueue;
    }
}
