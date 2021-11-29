package com.jemmy.jdk8;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhujiang.cheng
 * @since 2021/4/13
 */
public class StreamTest2 {

    public static void main(String[] args) {
        beforeJdk8();
        sinceJdk8();
    }

    private static void beforeJdk8() {
        List<String> myList = Lists.newArrayList(
            "bcd", "cde", "def", "abc"
        );
        List<String> result = Lists.newArrayListWithCapacity(myList.size());
        for (String str : myList) {
            if (str.length() >= 3) {
                char e = str.charAt(0);
                String tempStr = String.valueOf(e);
                result.add(tempStr);
            }
        }

        System.out.println(result);
    }

    private static void sinceJdk8() {
        List<String> myList = Lists.newArrayList(
            "bcd", "cde", "def", "abc"
        );
        List<String> result = myList.stream()
            .filter(s -> s.length() >= 3)
            .map(s -> s.charAt(0))
            .map(String::valueOf)
            .collect(Collectors.toList());

        System.out.println(result);
    }
}
