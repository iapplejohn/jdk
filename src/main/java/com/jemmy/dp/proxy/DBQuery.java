/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: DBQuery.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/19 11:48
 * Description: 
 */
package com.jemmy.dp.proxy;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * DBQuery
 *
 * @author Cheng Zhujiang
 * @date 2017/9/19
 */
public class DBQuery implements IDBQuery {

    public DBQuery() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String request() {
        return "request string";
    }
}
