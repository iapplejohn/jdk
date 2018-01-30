/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Out2.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/20 16:03
 * Description: 
 */
package com.jemmy.inner;

/**
 * <pre>
 * 方法作用域的内部类
 *
 * @author Cheng Zhujiang
 * @date 2017/9/20
 */
public class Outer2 {

    Inface getInface(boolean flag) {
        if (flag) {
            class Inner implements Inface {

                @Override
                public void sysout() {
                    System.out.println("876");
                }
            }
            return new Inner();
        } else {
            class Inner implements Inface {

                @Override
                public void sysout() {
                    System.out.println("123");
                }
            }
            return new Inner();
        }
    }

    public static void main(String[] args) {
        Outer2 outer = new Outer2();
        Inface inface = outer.getInface(true);
        inface.sysout();

        inface = outer.getInface(false);
        inface.sysout();
    }
}
