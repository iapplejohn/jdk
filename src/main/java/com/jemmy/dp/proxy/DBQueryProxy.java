/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: DBQueryProxy.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/19 11:50
 * Description: 
 */
package com.jemmy.dp.proxy;

/**
 * <pre>
 * DBQueryProxy
 *
 * @author Cheng Zhujiang
 * @date 2017/9/19
 */
public class DBQueryProxy implements IDBQuery {

    private DBQuery real = null;

    @Override
    public String request() {
        if (real == null) { // 在真正需要的时候，才创建真实对象，创建过程可能很慢
            real = new DBQuery();
        }
        // 在多线程环境下，这里返回一个虚假类，类似于Future模式
        return real.request();
    }
}
