/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Singleton.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/13 13:35
 * Description: 
 */
package com.jemmy.dp.singleton;

/**
 * <pre>
 * 单例-急加载,不加锁
 *
 * @author Cheng Zhujiang
 * @date 2017/9/13
 */
public class Singleton {

    private Singleton() {
        System.out.println("Singleton is initializing");
    }

    private static final Singleton instance = new Singleton(); // 急加载

    public static Singleton getInstance() {
        return instance;
    }
}
