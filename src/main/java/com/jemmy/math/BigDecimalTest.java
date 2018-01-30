/*
 * Copyright (C), 2014-2018, 杭州盎然科技有限公司
 * FileName: BigDecimalTest.java
 * Author:   Cheng Zhujiang
 * Date:     2018/1/25 16:14
 * Description: 
 */
package com.jemmy.math;

import java.math.BigDecimal;

/**
 * <pre>
 * BigDecimal优先级测试
 *
 * @author Cheng Zhujiang
 * @date 2018/1/25
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal first = new BigDecimal("10");
        BigDecimal result = first.subtract(new BigDecimal("3")).multiply(new BigDecimal("100"));
        System.out.println(result);
    }

}
