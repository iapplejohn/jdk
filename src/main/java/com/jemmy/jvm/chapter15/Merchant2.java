package com.jemmy.jvm.chapter15;

/**
 * 桥接方法
 *
 * @author zhujiang.cheng
 * @since 2020/5/27
 */
public class Merchant2 {

    public Number actionPrice(Customer customer) {
        return 0;
    }
}

class NativeMerchant extends Merchant2 {

    @Override
    public Double actionPrice(Customer customer) {
        return 0.0d;
    }
}