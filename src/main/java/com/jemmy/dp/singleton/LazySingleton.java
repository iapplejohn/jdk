/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: LazySingleton.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/13 13:46
 * Description: 
 */
package com.jemmy.dp.singleton;

/**
 * <pre>
 * 单例-懒加载,加锁
 *
 * @author Cheng Zhujiang
 * @date 2017/9/13
 */
public class LazySingleton {

    private LazySingleton() {
        System.out.println("LazySingleton is constructing...");
    }

    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
