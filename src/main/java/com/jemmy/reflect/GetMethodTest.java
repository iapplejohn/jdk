package com.jemmy.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射 - 获取成员方法测试
 * getMethods: 获取 public 修饰的方法（包含从父类继承的）、Object 类的方法
 * getDeclaredMethods: 获取该类的所有方法，不包括 Object 类的方法
 *
 * @author zhujiang.cheng
 * @since 2021/5/16
 */
public class GetMethodTest {

    public static void main(String[] args) {
        Method[] methods = Child.class.getMethods();
        System.out.println(Arrays.toString(methods));

        Method[] declaredMethods = Child.class.getDeclaredMethods();
        System.out.println(Arrays.toString(declaredMethods));
    }
}
