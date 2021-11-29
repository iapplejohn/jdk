package com.jemmy.reflect.sample;

import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujiang.cheng
 * @since 2021/6/11
 */
public class GetFieldValueTest {

    private static final Logger log = LoggerFactory.getLogger("GetFieldValue");

    public static void main(String[] args) {
        TaskSendResult obj = new TaskSendResult();
        getFieldValue(obj, "topic");
        getFieldValue(obj, "topic");
    }

    private static Object getFieldValue(Object obj, String field) {
        Class<?> claz = obj.getClass();
        Field f;
        Object fieldValue = null;
        try {
            // 没有做缓存，每次拿的都是新对象
            Field[] fields = claz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getName().equals(field)) {
                    // 没有做缓存，每次拿的都是新对象
                    // 不需要调下面的方法，直接用 fields[i]
                    f = claz.getDeclaredField(field);
                    f.setAccessible(true);
                    fieldValue = f.get(obj);
                }
            }
        } catch (Exception e) {
            log.error("get value error", e);
        }
        return fieldValue;
    }
}
