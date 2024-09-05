package com.jemmy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @author zhujiang.cheng
 * @since 2023/12/20
 */
public class RegTest {

    @Test
    public void test() {
//        String str = "[ var='B0' metric='' labels={payoutType=人民币付款单, status=Distributing} value=1 ], [ var='B1' metric='' labels={payoutType=外币付款单, status=Pending} value=3 ]";
//        String str = "[ var='B0' metric='' labels={payoutType=人民币付款单, status=Distributing} value=1 ]";
        String str = "[ var='B0' metric='' labels={menu=未知异常单} value=1 ], [ var='B1' metric='' labels={menu=未知异常单2} value=2 ]";
//        String str = "[ var='B0' metric='' labels={menu=未知异常单} value=1 ]";
        Pattern p = Pattern.compile("\\[[a-zA-Z0-9= ']+labels=\\{(\\S+(,\\s\\S+)*)} value=(\\d+) ](, )?");
//        Pattern p = Pattern.compile("\\[[a-zA-Z0-9= ']+labels=\\{(.*)} value=(\\d+) ](, )?"); // 多个只匹配成1个
        Matcher matcher = p.matcher(str);
        while (matcher.find()) {
            String s1 = matcher.group(0);
            System.out.println(s1);
        }
    }
}
