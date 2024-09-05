package com.jemmy.util;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author zhujiang.cheng
 * @since 2023/5/18
 */
public class StringUtil {

    // 包含普通字符串的，这里会报错，需要使用 commons-lang3 的 StringEscapeUtils 方法
    public static String unicodeToStr(String unicode) {
        StringBuilder builder = new StringBuilder(unicode.length());
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int index = Integer.parseInt(hex[i], 16);
            builder.append((char) index);
        }
        return builder.toString();
    }

    // 包含普通字符串，不会报错
    public static String unicodeToString(String unicode) {
        return StringEscapeUtils.unescapeJava(unicode);
    }
}
