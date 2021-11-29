package com.jemmy.lang;

import java.util.Arrays;

/**
 * @author zhujiang.cheng
 * @since 2021/3/3
 */
public class SplitTest {

    public static void main(String[] args) {
//        testAnd();
        testMultiple();
    }

    private static void testAnd() {
        String str = "A1FKXQEFE44T9C&isAmazonFulfilled=0&asin=B00T7FSPH4";
        String[] result = str.split("&");
        String[] result2 = str.split("\\&");
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
    }

    private static void testMultiple() {
        String str = "fabulous , good，未来，发展，,沁人心脾";
        String[] result = str.split(",");
        String[] result2 = str.split("，");
        String[] result3 = str.split(",|，");
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
        System.out.println(Arrays.toString(result3));
    }
}
