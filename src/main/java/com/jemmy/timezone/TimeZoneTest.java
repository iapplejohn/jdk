package com.jemmy.timezone;

import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author zhujiang.cheng
 * @since 2021/12/6
 */
public class TimeZoneTest {

    public static void main(String[] args) {
        System.out.println(ZoneId.systemDefault().getId());

        System.out.println(TimeZone.getDefault().toZoneId());

        Date date = new Date();
        System.out.println(date);
    }
}
