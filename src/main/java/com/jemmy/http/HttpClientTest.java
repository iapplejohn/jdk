package com.jemmy.http;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhujiang.cheng
 * @since 2023/5/17
 */
public class HttpClientTest {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "浙江仙风智能科技有限公司");
        jsonObject.put("socialCode", "91330103MA2H2l");
        JSONObject result = HttpClientUtils.doPost("http://localhost:8010/gateway-service/test", jsonObject);
        System.out.println("result = " + result);
    }
}
