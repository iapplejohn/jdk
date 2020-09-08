package com.jemmy.math;

/**
 * Integer var = ? 在-128 至 127 范围内的赋值，
 * Integer 对象是在 IntegerCache.cache 产 生，会复用已有对象
 * 这个区间内的 Integer 值可以直接使用==进行判断，
 * 但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，
 * 这是一个大坑，推荐使用 equals 方法进行判断。
 *
 * @author zhujiang.cheng
 * @since 2020/3/16
 */
public class IntegerCacheTest {

    /**
     * 注意: 如果i1和i2通过new Integer方式创建, i1 == i2为false
     * @param args
     */
    public static void main(String[] args) {
        Integer i1 = 3;
        Integer i2 = 3;
        Integer i3 = -287;
        Integer i4 = -287;
        System.out.println(String.format("i1 == i2 ? %s", i1 == i2));
        System.out.println(String.format("i3 == i4 ? %s", i3 == i4));
    }
}
