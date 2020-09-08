package com.jemmy.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiang.cheng
 * @since 2020/7/11
 */
public class ArrayListTest {

    public static void main(String[] args) {
//        integerTest();
//        stringTest();
        objectTest();
    }

    private static void integerTest() {
        List<Integer> list = new ArrayList<>();
        list.add(999932);
        list.add(999933);
        list.add(999935);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(list));
        result.add(list);
        System.out.println(result);
    }

    private static void stringTest() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ggg");
        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>(list));
        result.add(list);
        System.out.println(result);
    }

    private static void objectTest() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("jemmy", 28));
        list.add(new Person("tina", 24));
        list.add(new Person("john", 28));
        List<List<Person>> result = new ArrayList<>();
        result.add(new ArrayList<>(list));
        result.add(list);
        System.out.println(result);
    }
}
