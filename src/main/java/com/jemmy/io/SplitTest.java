package com.jemmy.io;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.IOUtils;

/**
 * @author zhujiang.cheng
 * @since 2024/1/25
 */
public class SplitTest {

    public static void main(String[] args) {
        Map<String, String> descriptionMap = new HashMap<>();
        Map<String, Integer> confidentialMap = new HashMap<>();

        Properties properties = new Properties();
        try {
            String content = "service-lightyear-base~inbound.repay.days.a~description=A 级别大客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.a~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.bbb~description=BBB 级别大客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.bbb~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.b~description=B 级别大客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.b~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.bb~description=BB 级别大客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.bb~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.aa~description=AA 级别大客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.aa~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.aaa~description=AAA 级别大客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.aaa~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.sm.a~description=A 级别中小客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.sm.a~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.sm.bbb~description=BBB 级别中小客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.sm.bbb~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.sm.b~description=B 级别中小客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.sm.b~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.sm.bb~description=BB 级别中小客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.sm.bb~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.sm.aa~description=AA 级别中小客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.sm.aa~confidential=0\n"
                + "service-lightyear-base~inbound.repay.days.sm.aaa~description=AAA 级别中小客户的入账还款天数\n"
                + "service-lightyear-base~inbound.repay.days.sm.aaa~confidential=0\n";
            properties.load(IOUtils.toBufferedReader(new StringReader(content)));
        } catch (Exception e) {
            throw new IllegalArgumentException("读取配置信息异常");
        }
        for (String property : properties.stringPropertyNames()) {
            String[] metaDataKeys = property.split("~");
            if (metaDataKeys.length != 3) {
                throw new IllegalArgumentException("元数据键格式不正确");
            }
            String metaDataValue = properties.getProperty(property);
            if (metaDataValue == null || metaDataValue.isEmpty()) {
                throw new IllegalArgumentException("元数据值为空");
            }
            String dataId = metaDataKeys[1];
            String propertyName = metaDataKeys[2];
            if ("description".equals(propertyName)) {
                descriptionMap.put(dataId, metaDataValue);
            } else if ("confidential".equals(propertyName)) {
                confidentialMap.put(dataId, Integer.valueOf(metaDataValue));
            }
        }
        System.out.println("properties = " + properties);
    }


    public static void main2(String[] args) {
        String str = "payment~payment.allinpay.gateway~description=allinpay api地址\n"
            + "payment~payment.allinpay.gateway~confidential=0\n"
            + "payment~payment.allinpay.userName~description=allinpay api用户名\n"
            + "payment~payment.allinpay.userName~confidential=0\n"
            + "payment~payment.allinpay.userPass~description=allinpay api密码\n"
            + "payment~payment.allinpay.userPass~confidential=0\n"
            + "payment~payment.allinpay.privatePassword~description=allinpay api 私钥\n"
            + "payment~payment.allinpay.privatePassword~confidential=0\n";

        String[] metaDataArr = str.split("\r\n");
        for (String metaDataItem : metaDataArr) {
            String[] metaDataItemArr = metaDataItem.split("=");
            if (metaDataItemArr.length != 2) {
                throw new IllegalArgumentException("元数据格式不正确");
            }
            String[] metaDataKeys = metaDataItemArr[0].split("~");
            if (metaDataKeys.length != 3) {
                throw new IllegalArgumentException("元数据格式不正确");
            }
        }
    }
}
