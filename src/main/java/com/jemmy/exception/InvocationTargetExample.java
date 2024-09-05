package com.jemmy.exception;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujiang.cheng
 * @since 2022/12/12
 */
public class InvocationTargetExample {

    private static final Logger log = LoggerFactory.getLogger(InvocationTargetExample.class);

    public int divideByZeroExample() {
        return 1 / 0;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        InvocationTargetExample targetExample = new InvocationTargetExample();

        Method method = InvocationTargetExample.class.getMethod("divideByZeroExample");

        try {
            method.invoke(targetExample);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            log.error("Invoke failed, cause: ", e.getCause());
        }
    }
}
