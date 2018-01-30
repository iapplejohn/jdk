/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Sum.java
 * Author:   Cheng Zhujiang
 * Date:     2017/7/21 14:10
 * Description: 
 */
package com.jemmy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sum
 *
 * @author Cheng Zhujiang
 * @date 2017/7/21
 */
public class Sum {

    public static BigDecimal sumBigDecimal(List<BigDecimal> list) {
        return list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal sumMapValue(Map<String, BigDecimal> map) {
        return map.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
//        return map.entrySet().stream().map(e -> e.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static void main(String[] args) {
        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.ONE);
        list.add(new BigDecimal("3.26"));
        list.add(new BigDecimal("100.45"));
        BigDecimal sumResult = sumBigDecimal(list);
        System.out.println(sumResult);

        Map<String, BigDecimal> map = new HashMap<>();
        map.put("one", BigDecimal.ONE);
        map.put("two", BigDecimal.valueOf(2));
        map.put("three", BigDecimal.valueOf(181));
        BigDecimal sumMapResult = sumMapValue(map);
        System.out.println(sumMapResult);
    }

}
