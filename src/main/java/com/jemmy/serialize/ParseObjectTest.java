package com.jemmy.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhujiang.cheng
 * @since 2021/8/27
 */
public class ParseObjectTest {

    public static void main(String[] args) {
        Map<String,String> linkedMap = new LinkedHashMap<String,String>();
        linkedMap.put("b","2");
        linkedMap.put("a","1");
        linkedMap.put("c","3");
        System.out.println(linkedMap);
        String jsonStr = JSON.toJSONString(linkedMap);
        System.out.println(jsonStr);

        jsonStr = "{\\\"b\":\"2\",\"a\":\"1\",\"c\":\"3\"}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        System.out.println(jsonObject);
    }
}
