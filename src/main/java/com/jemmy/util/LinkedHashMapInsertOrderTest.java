package com.jemmy.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author zhujiang.cheng
 * @since 2024/4/14
 */
public class LinkedHashMapInsertOrderTest {

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        // 遍历: 按 insertOrder
        Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + '=' + entry.getValue());
        }

        // 遍历: 按 insertOrder
//        for (Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + '=' + entry.getValue());
//        }
    }
}
