package com.jemmy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhujiang.cheng
 * @since 2023/4/24
 */
public class Test2 {

    public static void main(String[] args) {
        String sqlSegment = "secret_name IN abc";

//        Pattern PATTERN_FIELD_EXP = Pattern.compile("\\((\\w+)\\s");
//        Pattern PATTERN_PARAM_EXP = Pattern.compile("#\\{ew\\.paramNameValuePairs\\.(\\w+)}");
        Pattern PATTERN_VALUE_EXP =
//            Pattern.compile("(\\w+) (=|IN) #\\{ew\\.paramNameValuePairs\\.(\\w+)},?");
            Pattern.compile("(\\w+) (=|IN) \\(?(#\\{ew\\.paramNameValuePairs\\.(\\w+)},?)+\\)?");
//            Pattern.compile("(\\w+) \\=|IN (\\w+)");

        Matcher matcher = PATTERN_VALUE_EXP.matcher(sqlSegment);
        while (matcher.find()) {
            // 字段名
            String fieldName = matcher.group(1);
            // 参数名
            String paramName = matcher.group(2);

            System.out.println("fieldName = " + fieldName);
            System.out.println("paramName = " + paramName);
        }
    }
}
