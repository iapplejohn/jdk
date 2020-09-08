package com.jemmy.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhujiang.cheng
 * @since 2020/7/10
 */
public class ExecutorsTest {

    public void testSingle() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<?> future = service.submit(new Callable<Object>() {
            @Override
            public Object call() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " is finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }
        });

        try {
            Object result = future.get(10, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread() + ", result: " + result + ", future is done = " + future.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorsTest test = new ExecutorsTest();

        test.testSingle();

        System.gc();

        TimeUnit.SECONDS.sleep(100000000);
        System.out.println("System exit");
    }
}
