package com.jemmy.jvm.chapter16;

/**
 * 深入拆解Java虚拟机 - 16 | 即时编译（上）
 *
 * @author zhujiang.cheng
 * @since 2021/4/20
 */
public class JITTest {

    public static void foo(Object obj) {
        int sum = 0;
        for (int i = 0; i < 200; i++) {
            sum += i;
        }
    }
}
