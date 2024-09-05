package com.jemmy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhujiang.cheng
 * @since 2023/4/23
 */
public class Test {

    public static void main(String[] args) {
//        String sqlSegment = "(secret_name IN (#{ew.paramNameValuePairs.MPGENVAL1},#{ew.paramNameValuePairs.MPGENVAL2},#{ew.paramNameValuePairs.MPGENVAL3}))";

//        String sqlSegment = "(file_name = #{ew.paramNameValuePairs.MPGENVAL1})";

        String sqlSegment = "(file_name = #{ew.paramNameValuePairs.MPGENVAL1} AND secret_name IN (#{ew.paramNameValuePairs.MPGENVAL2},#{ew.paramNameValuePairs.MPGENVAL3}))";

//        Pattern PATTERN_FIELD_EXP = Pattern.compile("\\((\\w+)\\s");
//        Pattern PATTERN_PARAM_EXP = Pattern.compile("#\\{ew\\.paramNameValuePairs\\.(\\w+)}");
        Pattern PATTERN_VALUE_EXP =
//            Pattern.compile("(\\w+) (=|IN) #\\{ew\\.paramNameValuePairs\\.(\\w+)},?");
            Pattern.compile("(\\w+) (=|IN) (\\S+)");
//        Pattern PATTERN_PARAM_EXP = Pattern.compile("#\\{ew\\.paramNameValuePairs\\.(\\w+)}");
        Pattern PATTERN_PARAM_EXP = Pattern.compile("(MPGENVAL\\d+)");
//        Pattern.compile("(\\w+) (=|IN) \\(?[^)]+\\)?");
//            Pattern.compile("(\\w+) (=|IN) \\(([^,]+)(,[^,]+)*\\)");

//        Matcher matcher = PATTERN_FIELD_EXP.matcher(sqlSegment);
//        while (matcher.find()) {
//            String fieldName = matcher.group(1);
//            System.out.println("fieldName = " + fieldName);
//        }
//
//        Matcher matcher2 = PATTERN_PARAM_EXP.matcher(sqlSegment);
//        while (matcher2.find()) {
//            String paramName = matcher2.group(1);
//            System.out.println("paramName = " + paramName);
//        }

        Matcher matcher = PATTERN_VALUE_EXP.matcher(sqlSegment);
        while (matcher.find()) {
            // 字段名
            String fieldName = matcher.group(1);
            // 比较符
            String operateName = matcher.group(2);
            // 字段参数表达式
            String expr = matcher.group(3);

            System.out.println("fieldName = " + fieldName);
            System.out.println("paramExpr = " + expr);

            Matcher matcher2 = PATTERN_PARAM_EXP.matcher(expr);
            while (matcher2.find()) {
                // 参数表达式
                String paramName = matcher2.group(1);
                System.out.println("paramName = " + paramName);
            }
        }

        System.out.println("matcher = " + matcher);
    }
}
