package com.jemmy.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author zhujiang.cheng
 * @since 2024/9/12
 */
public class MyMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = -7947478861728845978L;

    public V put(K key1, K key2, V value) {
        if (containsKey(key1) || containsKey(key2)) {
            throw new IllegalArgumentException("已存在 " + key1  + " 或 " + key2);
        }
        V origin = put(key1, value);
        put(key2, value);
        return origin;
    }

    @Override
    public V remove(Object key) {
        V origin = super.remove(key);

        for (Iterator<Entry<K, V>> iterator = entrySet().iterator(); iterator.hasNext();) {
            Entry<K, V> entry = iterator.next();
            if (entry.getValue() == origin) {
                iterator.remove();
                break;
            }
        }
        return origin;
    }
}