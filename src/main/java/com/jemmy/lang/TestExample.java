package com.jemmy.lang;

/**
 * @author zhujiang.cheng
 * @since 2021/1/3
 */
public class TestExample {

    public static void main(String[] args) {
        TestExample example = new TestExample();
        int exampleCode = System.identityHashCode(example);
        int exampleCode2 = System.identityHashCode(example);
        int exampleHashcode = example.hashCode();
        System.out.println("example identityHashCode:" + exampleCode);
        System.out.println("example2 identityHashCode:" + exampleCode2);
        System.out.println("example Hashcode:" + exampleHashcode);

        String str = "dd";
        String str2 = "dd";
        int strCode = System.identityHashCode(str);
        int strHashCode = str.hashCode();
        int str2HashCode = str2.hashCode();
        System.out.println("str identityHashCode:" + strCode);
        System.out.println("str hashcode:" + strHashCode);
        System.out.println("str2 hashcode:" + str2HashCode);
    }
}
