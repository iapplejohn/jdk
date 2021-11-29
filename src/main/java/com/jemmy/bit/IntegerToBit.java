package com.jemmy.bit;

/**
 * @author zhujiang.cheng
 * @since 2021/10/30
 */
public class IntegerToBit {

    public static void main(String[] args) {
        Integer i = 5;
        System.out.println(Integer.toBinaryString(i));
        Integer i2 = -5;
        System.out.println(Integer.toBinaryString(i2));

        System.out.println(i & i2);
    }
}
