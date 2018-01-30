/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: WeakHashMapTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/13 16:54
 * Description: 
 */
package com.jemmy.ref;

import java.util.*;

/**
 * WeakHashMap使用弱引用，可以自动释放已经被回收的key所在的表项，
 * 但如果WeakHashMap的key都在系统内持有强引用，
 * 那么WeakHashMap就退化为普通的HashMap，因为所有的表项都无法被自动清理。
 *
 * @author Cheng Zhujiang
 * @date 2017/8/13
 */
public class WeakHashMapTest {

    public static void main(String[] args) {
        Map<Integer, Byte[]> map = new WeakHashMap<>(); // 使用WeakHashMap
        List l = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            //l.add(ii); // 强引用,WeakHashMap退化为HashMap
            map.put(ii, new Byte[i]);
        }

        map = new HashMap<>(); // 使用HashMap, 抛出异常"java.lang.OutOfMemoryError: Java Heap space"
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            map.put(ii, new Byte[i]);
        }
    }

}
