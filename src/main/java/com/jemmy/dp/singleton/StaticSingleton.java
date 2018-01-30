/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: StaticSingleton.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/13 13:39
 * Description: 
 */
package com.jemmy.dp.singleton;

/**
 * <pre>
 * 单例-懒加载,不加锁
 * 私有静态内部类,在外部类加载时不会被加载,借此实现懒加载
 * @author Cheng Zhujiang
 * @date 2017/9/13
 */
public class StaticSingleton {

    private StaticSingleton() {
        System.out.println("Singleton is initializing");
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
