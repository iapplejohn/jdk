package com.jemmy.proxy.spring;

import java.lang.reflect.InvocationTargetException;
import org.springframework.cglib.reflect.FastClass;

/**
 * @author zhujiang.cheng
 * @since 2021/6/9
 */
public class FastClassTest {

    public static void main(String[] args) throws InvocationTargetException {
        A a  = new A();
        FastClass fastA = FastClass.create(A.class);
        Class[] parameterTypes = new Class[] {String.class, TestInterface.class};
        Object[] params = new Object[] {"param1", new TestImpl()};
        fastA.invoke("fetch", parameterTypes, a, params);
        System.out.println("finish");
    }
}
