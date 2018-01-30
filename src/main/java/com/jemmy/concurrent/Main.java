/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Main.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/22 8:11
 * Description: 
 */
package com.jemmy.concurrent;

import java.util.concurrent.*;

/**
 * <pre>
 * Main
 *
 * @author Cheng Zhujiang
 * @date 2017/8/22
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 构造FutureTask
        FutureTask<String> future = new FutureTask<>(new RealData2("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // 执行FutureTask, 相当于上例中的client.request("a")发送请求
        // 在这里开启线程进行RealData的call()执行
        executor.submit(future);
        System.out.println("请求完毕");
        try {
            // 这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 相当于上例中的data.getResult()，取得call()方法返回值
        // 如果此时call()方法没有执行完成，则依然会等待
        System.out.println("数据 = " + future.get());
        executor.shutdown();
    }

}
