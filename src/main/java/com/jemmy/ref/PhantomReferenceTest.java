package com.jemmy.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @author zhujiang.cheng
 * @since 2020/4/12
 */
public class PhantomReferenceTest {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomRef = new PhantomReference<>(myObject, referenceQueue);
//        CheckRefQueueThread checkRefQueue = new CheckRefQueueThread();
//        checkRefQueue.setRefQueue(referenceQueue);
//        checkRefQueue.start();
        System.out.println("Before GC: Phantom Get= " + phantomRef.get());
        myObject = null;
        System.gc();
        System.out.println("After GC: Phantom Get= " + phantomRef.get());
        Reference<? extends MyObject> ref;
        while ((ref = referenceQueue.poll()) != null) {
            System.out.println("ref = " + ref);
        }
    }
}
