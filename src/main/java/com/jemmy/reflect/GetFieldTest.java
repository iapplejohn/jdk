package com.jemmy.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 反射 - 获取成员变量测试
 * getFields: 获取 public 修饰的成员变量，包含从父类继承的 public 成员变量
 *
 * getDeclaredFields: 获取该类所有的成员变量，包含 public, protected, private, package，
 * 不包含从父类继承的成员变量
 *
 * @author zhujiang.cheng
 * @since 2021/5/16
 */
public class GetFieldTest {

    public static void main(String[] args) {
        Field[] fields = Child.class.getFields();
        System.out.println(Arrays.toString(fields));

        Field[] declaredFields = Child.class.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
    }
}
