package com.jemmy.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

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
}
