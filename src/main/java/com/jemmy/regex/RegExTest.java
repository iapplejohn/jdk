/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: RegExTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/22 15:06
 * Description: 
 */
package com.jemmy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * RegExTest
 *
 * @author Cheng Zhujiang
 * @date 2017/12/22
 */
public class RegExTest {

    public static void main(String[] args) {
        String str = "\"url\":\"home\",\"userid\":\"\",\"version\":\"4.9.0\"";
        Pattern p = Pattern.compile("^\\\"url\\\"\\:\\\"home\\\"");
        Matcher matcher = p.matcher(str);
        while (matcher.find()) {
            String s1 = matcher.group(0);
            System.out.println(s1);
        }
    }

}
