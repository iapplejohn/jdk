package com.jemmy.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhujiang.cheng
 * @since 2020/7/14
 */
public class InvokeMethodTest {

    public boolean compare(Object a, Object b) {
        throw new IllegalArgumentException("ddd");
    }

    public String concat(String str1, String str2) {
        return str1.concat(str2);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = InvokeMethodTest.class.getMethod("compare", Object.class, Object.class);
        method = InvokeMethodTest.class.getMethod("concat", String.class, String.class);
        InvokeMethodTest instance = new InvokeMethodTest();
        Object arg1 = new Object(), arg2 = "ccc";
        Object[] args2 = new Object[] {arg1, arg2};
        // method = null; NullPointerException
        // instance = null; NullPointerException
        // arg1 = "3", arg2 = "8" // IllegalArgumentException argument type mismatch
        // arg1 = 3 // IllegalArgumentException wrong number of arguments
        try {
            Object result = method.invoke(instance, arg2);
            System.out.println("result = " + result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
