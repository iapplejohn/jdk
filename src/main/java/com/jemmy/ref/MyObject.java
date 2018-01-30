/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: MyObject.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/13 15:00
 * Description: 
 */
package com.jemmy.ref;

/**
 * MyObject
 *
 * @author Cheng Zhujiang
 * @date 2017/8/13
 */
public class MyObject {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyObject's finalize called"); // 被回收时输出
    }

    @Override
    public String toString() {
        return "I am MyObject";
    }
}
