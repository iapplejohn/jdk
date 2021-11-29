package com.jemmy.lang;

import java.util.Random;

/**
 * https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
 *
 * @author zhujiang.cheng
 * @since 2020/11/12
 */
public class ConstantPoolTest {

    public static void main2(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }

    public static void main3(String[] args) {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
    }

    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) {
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }

        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
//            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }

        System.out.println((System.currentTimeMillis()  - t) + "ms");
    }
}
