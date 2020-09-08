package com.jemmy.jvm.chapter15;

/**
 * @author zhujiang.cheng
 * @since 2020/5/27
 */
public class Merchant<T extends Customer> {

    public double actionPrice(T customer) {
        return 0.0d;
    }

    public static void main(String[] args) {
        Customer customer = new VIP();
//        new VIPOnlyMerchant().actionPrice(customer); // 编译出错
    }
}

class VIPOnlyMerchant extends Merchant<VIP> {

    @Override
    public double actionPrice(VIP customer) {
        return 0.0d;
    }
}