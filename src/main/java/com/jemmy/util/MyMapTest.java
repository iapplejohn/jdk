package com.jemmy.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试自定义 Map2
 *
 * @author zhujiang.cheng
 * @since 2024/9/13
 */
public class MyMapTest {

    @Test
    public void testMyMap() {
        Object obj = new Object();
        MyMap<String, Object> map = new MyMap<>();
        map.put("key1", "key2", obj);
        System.out.println(map.get("key1") == map.get("key2"));
        map.remove("key1");
        Object obj2 = map.get("key2");
        System.out.println("obj2 = " + obj2);
    }

    @Test
    public void testMyMapO1() {
        Object obj = new Object();
        Object obj4 = new Object();
        MyMapO1<String, Object> map = new MyMapO1<>();
        map.put("key1", "key2", obj);
        // map.put("key1", "key3", obj); // 抛异常
        map.put("key3", "key4", obj4);
        Assert.assertSame(map.get("key1"), map.get("key2"));
        Assert.assertSame(map.get("key3"), map.get("key4"));

        map.remove("key1");
        Object obj2 = map.get("key2");
        Assert.assertNull(obj2);

        map.remove("key4");
        Object obj3 = map.get("key3");
        Assert.assertNull(obj3);
    }
}
