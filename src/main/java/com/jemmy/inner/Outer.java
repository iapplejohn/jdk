/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Outer.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/20 14:01
 * Description: 
 */
package com.jemmy.inner;

/**
 * <pre>
 * 方法内部的内部类
 *
 * @author Cheng Zhujiang
 * @date 2017/9/20
 */
public class Outer {

    private InnerInterface getStr() {
        class Inner implements InnerInterface {
            String sss = "";

            @Override
            public String getStr1(String xxx) {
                sss = xxx;
                return sss;
            }
        }

        return new Inner();
    }

    public static void main(String[] args) {
        InnerInterface innerInterface = new Outer().getStr();
        System.out.println(innerInterface.getStr1("1234"));
    }

}
