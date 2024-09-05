package com.jemmy.lang;

/**
 * https://juejin.cn/post/6844904036873814023
 *
 * @author zhujiang.cheng
 * @since 2022/4/14
 */
public class StringLengthTest {

    public static void main(String[] args) {
        String b = "🎵"; // 这个就是那个音符字符
        String c = "\uD834\uDD1E"; // 这个就是音符字符的UTF-16编码
        System.out.println(c);
        System.out.println(b.length());
        System.out.println(b.codePointCount(0, b.length()));
    }
}
