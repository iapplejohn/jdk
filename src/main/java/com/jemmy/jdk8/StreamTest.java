/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: StreamTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/16 10:12
 * Description: 
 */
package com.jemmy.jdk8;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <pre>
 * StreamTest
 *
 * @author Cheng Zhujiang
 * @date 2017/12/16
 */
public class StreamTest {

    public static void main(String[] args) {
        Predicate<String> preFirst = p -> p.equals("expected");
        Predicate<String> preSecond = StringUtils::isEmpty;
        List<String> list = new ArrayList<>();
        list.add("Brilliant");
        list.add("");
        list.add("Fabulous");
        list.add("expected");

        // Priority filter
        // Solution 1: anyMatch
        String str = list.stream().filter(list.stream().anyMatch(preFirst) ? preFirst : preSecond).findFirst().orElse(null);
        System.out.println(str);

        // Solution 2: or
        final String[] str2 = new String[1];
        Optional.of(list.stream()
                .filter(preFirst.or(preSecond))
                .collect(Collectors.groupingBy(preFirst::test)))
        .map(m -> m.get(m.containsKey(true)))
        .ifPresent(strings -> str2[0] = strings.get(0));
        System.out.println(str2[0]);
    }

}
