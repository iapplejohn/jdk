package com.jemmy.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * 需要将json字符串压缩为一行，去除空格换行制表符，用 fastjson 会改变字符串的顺序
 *
 * @author zhujiang.cheng
 * @since 2022/9/20
 */
public class JSONTest {

    public static void main(String[] args) {
        // Feature.OrderedField 不会改变原来的顺序
        JSONObject jsonObject = JSON.parseObject("{\n"
            + "    \"c\":\"fabulous\",\n"
            + "    \"b\":\"gorgeous\",\n"
            + "    \"d\":\"delicious\"\n"
            + "}", Feature.OrderedField);
        String result = JSON.toJSONString(jsonObject, false);
        System.out.println(result);
    }
}
