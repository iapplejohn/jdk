package com.jemmy.loop;

/**
 * 如何快速定位无限循环
 * https://www.jianshu.com/p/8955b04b2b80
 *
 * @author zhujiang.cheng
 * @since 2020/5/25
 */
public class EndlessLoopDemo {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i--) {
            System.out.println("endless loop!");
        }
    }

}
