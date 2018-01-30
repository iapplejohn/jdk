/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: BadClassTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/24 14:34
 * Description: 
 */
package com.jemmy.cl;

/**
 * <pre>
 * BadClassTest
 *
 * @author Cheng Zhujiang
 * @date 2017/9/24
 */
public class BadClassTest {

    public static void main(String[] args) {
        try {
            BadClass.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BadClass.doSomething();
    }
}
