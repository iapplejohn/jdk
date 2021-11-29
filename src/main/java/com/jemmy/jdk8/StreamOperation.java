package com.jemmy.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhujiang.cheng
 * @since 2021/4/13
 */
public class StreamOperation {

    public static void main(String[] args) {
//        filterTest();
//        sortedTest();
//        mapTest();
//        flatMapTest();
//        forEachTest();
//        collectTest();
//        collectToMapTest();
//        customizeCollector();
//        reduceTest();
        reduceTest2();
        reduceTest3();
    }

    // -------- 常用中间操作 ----------
    private static void filterTest() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        // 获取空字符串的数量
        long count = strings
            .stream()
            .filter(String::isEmpty)
            .count();
        System.out.println(count);
    }

    private static void sortedTest() {
        List<String> myList = Arrays.asList("15", "2", "33");
        myList.stream()
            .sorted(Comparator.comparingInt(Integer::parseInt))
            .forEach(System.out::println);
    }

    private static void mapTest() {
        List<String> myList = Arrays.asList("a1", "a2", "a3");
        myList.stream()
            .map(s -> Integer.parseInt(s.substring(1)))
            .forEach(System.out::println);
    }

    private static void flatMapTest() {
        Stream<List<Integer>> integerListSteam = Stream.of(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4),
            Arrays.asList(5)
        );
        Stream<Integer> integerStream = integerListSteam.flatMap(list -> list.stream());
        integerStream.forEach(System.out::println);
    }

    // -------- 常用结束操作 ----------
    // --------- forEach ---------
    private static void forEachTest() {
        List<String> myList = Arrays.asList("1", "2", "3");
        myList.stream()
            .forEach(System.out::println);
    }

    // --------- collect ---------
    private static void collectTest() {
        List<String> myList = Arrays.asList("a1", "b2", "c3");
        List<String> filtered = myList.stream()
            .filter(s -> !s.startsWith("a"))
            .collect(Collectors.toList());
        System.out.println(filtered);

        myList = Arrays.asList("a1", "b2", "c3");
        String merged = myList.stream()
            .filter(s -> !s.startsWith("a"))
            .collect(Collectors.joining(","));
        System.out.println(merged);
    }

    // --------- collect to map ---------
    private static void collectToMapTest() {
        List<String> myList = Arrays.asList("a1", "b2", "c3", "c4");
        // 将 Stream 元素转换成 map 的时候，需要特别注意：key 必须是唯一的，否则会抛出 IllegalStateException。
        // 但是我们可以传入一个 merge function，来指定重复的元素映射的方式
        Map<String, String> map = myList.stream()
            .collect(Collectors.toMap(
                s -> s.substring(0, 1), // keyMapper
                s -> s.substring(1, 2), // valueMapper
                (val1, val2) -> val1 + "," + val2 // mergeFunction：合并key重复元素
            ));

        System.out.println(map);
    }

    // --------- customized collector ---------
    private static void customizeCollector() {
        List<String> myList = Arrays.asList("1", "2", "3");
        String joined = myList.stream()
            .sorted(Comparator.comparingInt(Integer::parseInt))
//            .collect(Collectors.joining("|"));
            .collect(Collector.of
                (() -> new StringJoiner("|"), // supplier：创建一个空的累加器实例
                    ((joiner, s) -> joiner.add(s)), // accumulator：第一个参数是累计值，第二参数是第n个元素。累加值与元素n如何做运算就是accumulator做的事情
                    (joiner1, joiner2) -> joiner1.add(joiner2.toString()),  // combiner：并行执行后才会调用，在combiner中对并行流的各个子部分的累加值结果合并为一个累加值结果
                    joiner -> joiner.toString() // finisher：参数是累加值，返回的结果就是最终的结果。finisher就是将累加器对象转换为整个集合操作的最终结果
                ));
        System.out.println(joined);
    }

    // --------- Reduce ---------
    private static void reduceTest() {
        List<String> myList = Arrays.asList("a1", "b2", "c3");
        // 将 Stream 中的元素聚合成一个
        Optional<String> reduced = myList.stream()
            .sorted()
            .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
    }

    private static void reduceTest2() {
        // 多接收一个初始对象。通常可以用于聚合操作（比如累加）
        int sum = Stream.of(1, 2, 3, 4)
            .reduce(0, // 初始值 identity
                (acc, element) -> acc + element // 合并元素操作 accumulator
            );
        System.out.println(sum);
    }

    private static void reduceTest3() {
        // 求单词长度之和
        Stream<String> stream = Stream.of("a", "bb", "ccc", "dddd");
        int lengthSum = stream.reduce(0, // 初始值　// (1)
            (sum, str) -> sum + str.length(), // 累加器 // (2)
            (a, b) -> a + b); // 部分结果拼接器，并行执行(parallelStream)时才会用到，非并行执行(stream)不会被调用到 // (3)
        // 等同于 int lengthSum = stream.mapToInt(String::length).sum();

        System.out.println(lengthSum);
    }
}
