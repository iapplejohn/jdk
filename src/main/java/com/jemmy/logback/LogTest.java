/*
 * Copyright (C), 2014-2018, 杭州盎然科技有限公司
 * FileName: LogTest.java
 * Author:   Cheng Zhujiang
 * Date:     2018/1/19 18:04
 * Description: 
 */
package com.jemmy.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * LogTest
 *
 * @author Cheng Zhujiang
 * @date 2018/1/19
 */
public class LogTest {

    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
//        wrongLogThrowable();
        rightLogThrowable();
    }

    private static void wrongLogThrowable() {
        try {
            int test = 10/0;
        } catch (Exception e) {
            log.error("error" + e);
            //main  ERROR c.d.s.LogThrowableRule -
            //                         errorjava.lang.ArithmeticException: / by zero
        }
    }

    private static void rightLogThrowable() {
        try {
            int test = 10/0;
        } catch (Exception e) {
            log.error("error" + ":", e);
            //main  ERROR c.d.s.LogThrowableRule -
            //                         errorjava.lang.ArithmeticException: / by zero
        }
    }
}
