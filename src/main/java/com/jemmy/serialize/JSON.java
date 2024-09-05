package com.jemmy.serialize;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON工具类,底层使用jackson
 *
 * @author zhujiang.cheng
 * @since 2020/6/2
 */
public class JSON {

    private static final Logger log = LoggerFactory.getLogger(JSON.class);

    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        // 序列化: 不包含NULL的属性
        mapper.setSerializationInclusion(Include.NON_NULL);
        // 反序列化: 忽略未知的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {

            private static final long serialVersionUID = -2846013992948315654L;

            @Override
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                String name = method.getName();
                if (name.length() > 4 && Character.isUpperCase(name.charAt(4))) {
                    return Character.toLowerCase(name.charAt(3)) + name.substring(4);
                } else {
                    return defaultName;
                }
            }

            @Override
            public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                String name = method.getName();
                if (name.length() > 4 && Character.isUpperCase(name.charAt(4))) {
                    return Character.toLowerCase(name.charAt(3)) + name.substring(4);
                } else {
                    return defaultName;
                }
            }
        });
    }

    public static String toJSONString(Object obj) {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("序列化" + obj + "异常" , e);
        }
        return json;
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        T result = null;
        try {
            result = mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("反序列化" + json + "异常" , e);
        }

        return result;
    }

    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        List<T> result = null;
        try {
            JavaType type = TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);
            result = mapper.readValue(json, type);
        } catch (IOException e) {
            log.error("反序列化" + json + "异常" , e);
        }

        return result;
    }
}
