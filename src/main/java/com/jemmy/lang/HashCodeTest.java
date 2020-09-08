package com.jemmy.lang;

import java.util.HashMap;

/**
 * @author zhujiang.cheng
 * @since 2020/4/14
 */
public class HashCodeTest {

    public static void main(String[] args) {
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();
        int hashCode1 = map1.hashCode();
        int hashCode2 = map2.hashCode();
        System.out.println("hashCode1 = " + hashCode1);
        System.out.println("hashCode2 = " + hashCode2);
        map1.put("test", "123");
        map2.put("test", "123");
        hashCode1 = map1.hashCode();
        hashCode2 = map1.hashCode();
        System.out.println("hashCode1 = " + hashCode1);
        System.out.println("hashCode2 = " + hashCode2);
    }

}
