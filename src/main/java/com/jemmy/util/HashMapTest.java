package com.jemmy.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author zhujiang.cheng
 * @since 2024/4/14
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("abc", "1");
        map.put("bdf", "2");
        map.put("ccc", "3");
        map.put("dee", "4");

        // 遍历 table 位置（每个位置的链表）
        Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + '=' + entry.getValue());
        }
    }
}
