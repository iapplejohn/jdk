package com.jemmy.jdk8;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 创建 Stream
 *
 * @author zhujiang.cheng
 * @since 2021/4/13
 */
public class StreamCreation {

    public static void main(String[] args) {
//        fromList();
//        fromStream();
//        fromIntStream();
//        objToBasic();
        basicToObj();
    }

    private static void fromList() {
        // 从List创建
        Arrays.asList("a1", "a2", "a3")
            .stream()
            .forEach(s -> System.out.print(s + ", "));
    }

    private static void fromStream() {
        // 直接从Stream创建，注意，Stream.of 的参数如果是 List，会当做一个对象
        Stream.of("a1", "a2", "a3")
            .forEach(s -> System.out.print(s + ", "));
    }

    private static void fromIntStream() {
        // 注意 range 的第二个参数是 exclusive
        IntStream.range(1, 3)
            .max()
            .ifPresent(System.out::println);
    }

    private static void objToBasic() {
        // 普通对象 Stream 可以通过 mapToInt() mapToLong() mapToDouble() 转换成基本类型 Stream
        Stream.of("s1", "s2", "s3")
            .map(s -> s.substring(1))
            .mapToInt(Integer::parseInt)
            .max()
            .ifPresent(System.out::println);
    }

    private static void basicToObj() {
        // 注意：'s' + i 返回为整型表示的字符
        IntStream.range(1, 4)
            .mapToObj(i -> "s" + i)
            .forEach(System.out::println);
    }
}
