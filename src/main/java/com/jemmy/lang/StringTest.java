package com.jemmy.lang;

/**
 * @author zhujiang.cheng
 * @since 2020/6/15
 */
public class StringTest {

    public static void main(String[] args) {
        String str = new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
        System.out.println(str);
    }
}
