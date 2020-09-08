package com.jemmy.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhujiang.cheng
 * @since 2020/3/17
 */
public class ListToArrayTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("fabulous");
        list.add("brilliant");

        String[] targetArray = new String[] {};
        String[] result = list.toArray(targetArray);

        // case 1: 向列表添加元素，不影响数组元素
        list.add("awesome");
        // case 2: 修改数组元素，不影响列表
        result[1] = "fantastic";
        System.out.println(list);
        System.out.println(Arrays.toString(targetArray));
        System.out.println(Arrays.toString(result));
    }
}
