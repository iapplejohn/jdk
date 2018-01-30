/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: BadClass.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/24 14:33
 * Description: 
 */
package com.jemmy.cl;

/**
 * <pre>
 * clinit方法在我们第一次主动使用这个类的时候会触发执行，
 * 比如我们访问这个类的静态方法或者静态字段就会触发执行clinit，
 * 但是这个过程是不可逆的，也就是说当我们执行一遍之后再也不会执行了，
 * 如果在执行这个方法过程中出现了异常没有被捕获，那这个类将永远不可用，
 * 虽然我们上面执行BadClass.doSomething()的时候catch住了异常，
 * 但是当代码跑到这里的时候，在jvm里已经将这个类打上标记了，说这个类初始化失败了，
 * 下次再初始化的时候就会直接返回并抛出类似的异常java.lang.NoClassDefFoundError:
 * Could not initialize class BadClass，而不去再次执行初始化的逻辑，
 *
 * @author Cheng Zhujiang
 * @date 2017/9/24
 */
public class BadClass {

    private static int a = 100;

    static {
        System.out.println("before init");
        int b = 3 / 0;
        System.out.println("after init");
    }

    public static void doSomething() {
        System.out.println("do something");
    }
}
