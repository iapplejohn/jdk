package com.jemmy.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiang.cheng
 * @since 2024/9/12
 */
public class MyMapO1<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = -7947478861728845978L;

    private Map<K, K> key1Key2Map = new HashMap<>();
    private Map<K, K> key2Key1Map = new HashMap<>();

    public V put(K key1, K key2, V value) {
        if (key1 == null || key2 == null) {
            throw new IllegalArgumentException("key1 或 key2 不能为 null");
        }
        if (containsKey(key1) || containsKey(key2)) {
            throw new IllegalArgumentException("已存在 " + key1  + " 或 " + key2);
        }
        V origin = put(key1, value);
        put(key2, value);
        key1Key2Map.put(key1, key2);
        key2Key1Map.put(key2, key1);
        return origin;
    }

    @Override
    public V remove(Object key) {
        V origin = super.remove(key);

        K anotherKey = key1Key2Map.get(key);
        if (anotherKey == null) {
            anotherKey = key2Key1Map.get(key);
        }
        if (anotherKey != null) {
            super.remove(anotherKey);
        }
        return origin;
    }
}