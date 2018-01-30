/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Client.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/3 12:44
 * Description: 
 */
package com.jemmy.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Client
 *
 * @author Cheng Zhujiang
 * @date 2017/8/3
 */
public class Client {

    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            @Override
            public void run() { // RealData的构建很慢，所以在单独的线程中进行
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            // 这里用一个sleep代替对其他业务逻辑的处理
            // 在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 = " + data.getResult());
    }

}
