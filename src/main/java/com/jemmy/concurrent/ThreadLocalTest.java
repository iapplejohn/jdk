package com.jemmy.concurrent;

/**
 * @author zhujiang.cheng
 * @since 2022/4/20
 */
public class ThreadLocalTest {

    static ThreadLocal<ParseContext> threadLocal = new ThreadLocal<>();

    public void process(ParseContext context) {
        try {
            threadLocal.set(context);
            step1();
            step2();
        } finally {
            threadLocal.remove();
        }
    }

    private void step1() {
        ParseContext context1 = threadLocal.get();
        System.out.println(context1);
    }

    private void step2() {
        ParseContext context2 = threadLocal.get();
        System.out.println(context2);
    }

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        ParseContext context = new ParseContext();
        context.setName("jemmy");
        context.setAge(20);
        test.process(context);
        System.out.println("finished");
    }

}
