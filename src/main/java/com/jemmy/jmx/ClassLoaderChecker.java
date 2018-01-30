/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: ClassLoaderChecker.java
 * Author:   Cheng Zhujiang
 * Date:     2017/11/3 15:44
 * Description: 
 */
package com.jemmy.jmx;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * <pre>
 * ClassLoaderChecker
 *
 * @author Cheng Zhujiang
 * @date 2017/11/3
 */
public class ClassLoaderChecker {

    public static void main(String[] args) {
        ClassLoadingMXBean bean = ManagementFactory.getClassLoadingMXBean();
        System.out.println("Loaded class count = " + bean.getLoadedClassCount());
        System.out.println("Total loaded class count = " + bean.getTotalLoadedClassCount());
        System.gc();
        System.out.println("Unloaded class count = " + bean.getUnloadedClassCount());
    }

}
