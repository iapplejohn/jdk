package com.jemmy.bit;

/**
 * @author zhujiang.cheng
 * @since 2020/7/25
 */
public class BitUtil {

    public static long combineInt2Long(int low, int high) {
        long value = ((long) low & 0xFFFFFFFFL) | ((long) high << 32 & 0xFFFFFFFF00000000L);
        return value;
    }

    public static void main(String[] args) {
        long result = combineInt2Long(1999339999, 1341324312);
        System.out.println(result);

    }
}
