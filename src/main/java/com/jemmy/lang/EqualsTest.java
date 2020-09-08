package com.jemmy.lang;

/**
 *
 * 垃圾回收后，hashCode会不会变
 *
 * @author zhujiang.cheng
 * @since 2020/4/14
 */
public class EqualsTest {

    public static void main(String[] args) {
//        integerTest();
//        stringTest();
        objectTest();
    }

    private static void integerTest() {
        Integer a = 10000;
        Integer b = 10000;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        int aHashCode = a.hashCode();
        int bHashCode = b.hashCode();
        System.out.println(aHashCode);
        System.out.println(bHashCode);
    }

    private static void stringTest() {
        String s1 = new String("Lina");
        String s2 = new String("Lina");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        int aHashCode = s1.hashCode();
        int bHashCode = s2.hashCode();
        System.out.println(aHashCode);
        System.out.println(bHashCode);
    }

    private static void objectTest() {
        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(o1 == o2);
        System.out.println(o1.equals(o2));
        int aHashCode = o1.hashCode();
        int bHashCode = o2.hashCode();
        System.out.println(aHashCode);
        System.out.println(bHashCode);
        long[] data = new long[10000];
        for (int i = 0; i < 10000; i++) {
            data[i] = i + 1;
        }
        System.gc();
        System.gc();
        aHashCode = o1.hashCode();
        bHashCode = o2.hashCode();
        System.out.println(aHashCode);
        System.out.println(bHashCode);
    }
}
