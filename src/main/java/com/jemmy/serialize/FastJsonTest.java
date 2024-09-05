package com.jemmy.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhujiang.cheng
 * @since 2020/5/14
 */
public class FastJsonTest {

    public static void main(String[] args) {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(false);
//        ParserConfig.getGlobalInstance().addAccept("com.jemmy");
//        ParserConfig.getGlobalInstance().setSafeMode(true);
//        ParserConfig.getGlobalInstance().addAccept("com.jemmy");

        Wrapper<Person> wrapper = buildGeneric();

        String json = JSON.toJSONString(wrapper);
//        String json = JSON.toJSONString(wrapper, SerializerFeature.WriteClassName);
        System.out.println(json);

        Object obj = JSON.parseObject(json, wrapper.getClass());
        System.out.println(obj);
    }

    private static void simpleTest() {
        Person person = buildPerson();

        String json = JSON.toJSONString(person);
        System.out.println(json);

        Object obj = JSON.parseObject(json, Person.class);
        System.out.println(obj);
    }

    private static JSONObject jsonObjectTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        return jsonObject;
    }

    private static JSONArray jsonArrayTest() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("first");
        jsonArray.add("second");
        return jsonArray;
    }

    /**
     * 泛型测试
     *
     * @return
     */
    private static Wrapper<Person> buildGeneric() {
        Wrapper<Person> wrapper = new Wrapper<>();
        wrapper.setName("john");
        wrapper.setT(buildPerson());
        return wrapper;
    }

    private static Person buildPerson() {
        Address address = new Address();
        address.setDetail("431dfa");
        Person person = new Person();
        person.setName("good");
        person.setAddress(address);
        return person;
    }

    // ------------ 属性特殊格式测试 ----------------
    @Test
    public void aCodeTest() {
        String json = "{\"aCode\":\"Terrific\"}";
        ACodeObj aCodeObj = JSON.parseObject(json, ACodeObj.class);
        Assert.assertNotNull(aCodeObj.getACode());
    }

    @Data
    static class ACodeObj {

        private String aCode;
    }

    @Test
    public void aTest() {
        String json = "{\"A\":\"Terrific\"}";
        AObj aObj = JSON.parseObject(json, AObj.class);
        Assert.assertNotNull(aObj.getA());
    }

    @Data
    static class AObj {

        private String A;
    }

    @Test
    public void URLTest() {
        String json = "{\"URL\":\"Terrific\"}";
        URLObj urlObj = JSON.parseObject(json, URLObj.class);
        Assert.assertNotNull(urlObj.getURL());
    }

    @Data
    static class URLObj {

        private String URL;
    }

    @Test
    public void urlTest() {
        String json = "{\"URL\":\"Terrific\"}";
        urlObj urlObj = JSON.parseObject(json, urlObj.class);
        Assert.assertNotNull(urlObj.getUrl());
    }

    @Data
    static class urlObj {

        private String url;
    }
}
