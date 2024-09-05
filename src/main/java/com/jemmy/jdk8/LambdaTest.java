package com.jemmy.jdk8;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author zhujiang.cheng
 * @since 2022/7/3
 */
public class LambdaTest {

    public static void main(String[] args) {
        System.out.println(listNormalWay());
        System.out.println(listNormalWay());
    }

    public static List<String> listNormalWay() {
        List<String> names = new ArrayList<>();
        names.add("Jemmy");
        names.add("Tina");
        List<String> lowercaseNames = new ArrayList<>();
        for (String name : names) {
            lowercaseNames.add(name.toLowerCase());
        }

        return lowercaseNames;
    }

    public static List<String> list2Stream() {
        List<String> names = new ArrayList<>();
        names.add("Jemmy");
        names.add("Tina");
        List<String> lowercaseNames = FluentIterable.from(names).transform(new Function<String, String>() {

            @Override
            public @Nullable String apply(@Nullable String name) {
                return name.toLowerCase();
            }
        }).toList();

        return lowercaseNames;
    }
}
