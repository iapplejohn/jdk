package com.jemmy.util;

import java.util.Arrays;
import java.util.List;

/**
 * 使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，
 * 它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常。
 *
 * 说明:asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。
 * Arrays.asList 体现的是适 配器模式，只是转换接口，后台的数据仍是数组。
 *
 * @author zhujiang.cheng
 * @since 2020/3/16
 */
public class ArraysTest {

    public static void main(String[] args) {
        String[] str = new String[] { "yang", "hao" };
        List list = Arrays.asList(str);
        // case 1: 运行时异常
//        list.add("yangguanbao");

        // case 2: 也会随之修改，反之亦然。
        str[0] = "changed";
        System.out.println(list);
    }

}
