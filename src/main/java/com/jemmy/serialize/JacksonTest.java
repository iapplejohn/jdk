package com.jemmy.serialize;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhujiang.cheng
 * @since 2020/6/1
 */
public class JacksonTest {

    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        // 序列化: 不包含NULL的属性
        mapper.setSerializationInclusion(Include.NON_NULL);
        // 反序列化: 忽略未知的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(String[] args) throws JsonProcessingException {
        simpleTest();
        genericTest();
    }

    private static void simpleTest() throws JsonProcessingException {
        Wrapper<Person> wrapper = buildGeneric();

        String json = mapper.writeValueAsString(wrapper);

        System.out.println(json);

        Object obj = mapper.readValue(json, wrapper.getClass());
        System.out.println(obj);
    }

    private static void genericTest() throws JsonProcessingException {
        Wrapper<Person> wrapper = buildGeneric();

        String json = mapper.writeValueAsString(wrapper);

        System.out.println(json);

        JavaType javaType = mapper.getTypeFactory().constructParametricType(Wrapper.class, Person.class);
        TypeReference<Wrapper<Person>> typeReference = new TypeReference<Wrapper<Person>>() {};

        Object obj = mapper.readValue(json, javaType);
        Object obj2 = mapper.readValue(json, typeReference);
        Object obj3 = mapper.readValue(json, WrapperType.class);

        System.out.println(obj);
        System.out.println(obj2);
        System.out.println(obj3);
    }

    /**
     * 泛型测试
     */
    private static Wrapper<Person> buildGeneric() {
        Wrapper<Person> wrapper = new Wrapper<>();
        wrapper.setName("john");
        wrapper.setT(buildPerson());
        return wrapper;
    }

    private static Person buildPerson() {
        Address address = new Address();
        address.setDetail("Hangzhou");
        Person person = new Person();
        person.setName("fabulous");
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

    // 如果有 urlObj，报错：
    // java.lang.NoClassDefFoundError: com/jemmy/serialize/JacksonTest$URLObj (wrong name: com/jemmy/serialize/JacksonTest$urlObj2)
    // 如果没有 urlObj，属性赋值失败
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
        String json = "{\"url\":\"Terrific\"}";
        urlObj2 urlObj2 = JSON.parseObject(json, urlObj2.class);
        Assert.assertNotNull(urlObj2.getUrl());
    }

    @Data
    static class urlObj2 {

        private String url;
    }

}
