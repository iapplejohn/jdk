package com.jemmy.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujiang.cheng
 * @since 2022/12/14
 */
public class LinkedHashMapTest {

    private static final Logger log = LoggerFactory.getLogger(LinkedHashMapTest.class);

    public static void main(String[] args) {
        Map<String, String> lruItem = new LinkedHashMap<>(16, 0.75f, true);
        lruItem.put("1", "1");
        lruItem.put("2", "2");
        lruItem.put("3", "3");
        lruItem.put("4", "4");

        List<String> list = Arrays.asList("3", "2", "5", "6", "7", "8");
//        List<String> list = Arrays.asList("5", "6", "7", "8");
         removeNotExisted(lruItem, list);
//        fetchFirstExisted(lruItem, list);
    }

    private static void fetchFirstExisted(Map<String, String> lruItem, List<String> list) {
        for (String item : list) {
            if (!lruItem.containsKey(item)) {
                lruItem.put(item, item);
            }
        }

        // load
        for (Entry<String, String> entry : lruItem.entrySet()) {
            String eldestKey = entry.getKey();
            if (list.contains(eldestKey)) {
                log.info("eldestValue: {}", entry.getValue());
                break;
            }
        }
    }

    private static void removeNotExisted(Map<String, String> lruItem, List<String> list) {
        lruItem.entrySet().removeIf(entry -> !list.contains(entry.getKey()));

        for (String item : list) {
            if (!lruItem.containsKey(item)) {
                lruItem.put(item, item);
            }
        }

        // load
        String eldestKey = lruItem.entrySet().iterator().next().getKey();
        String eldestValue = lruItem.get(eldestKey);
        log.info("eldestValue: {}", eldestValue);
    }
}
