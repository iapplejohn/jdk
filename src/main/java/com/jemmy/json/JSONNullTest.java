package com.jemmy.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author zhujiang.cheng
 * @since 2023/5/15
 */
public class JSONNullTest {

    public static void main(String[] args) {
        JSONObject jsonObject = JSON.parseObject("{\n"
            + "    \"c\":\"fabulous\",\n"
            + "    \"b\":\"gorgeous\",\n"
            + "    \"searchResults\":[{\"asin\": \"B07QLQHT43\", \"boughtTimeUnit\":null}]\n"
            + "}");
        JSONArray array = jsonObject.getJSONArray("searchResults");
        String withNullValue = array.toString(SerializerFeature.WriteMapNullValue);
        System.out.println("withNullValue = " + withNullValue);
        System.out.println("withoutNullValue = " + array);
    }
}
