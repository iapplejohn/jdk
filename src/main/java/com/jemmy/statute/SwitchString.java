package com.jemmy.statute;

/**
 * 阿里巴巴编码规范
 *
 * @author zhujiang.cheng
 * @since 2020/2/29
 */
public class SwitchString {

    public static void main(String[] args) {
        method(null);
    }

    private static void method(String param) {
        switch (param) { // 抛出NPE
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }

}
