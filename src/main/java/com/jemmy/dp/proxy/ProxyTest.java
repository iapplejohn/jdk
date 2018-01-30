/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: ProxyTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/23 14:56
 * Description: 
 */
package com.jemmy.dp.proxy;

import javassist.*;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * <pre>
 * ProxyTest
 *
 * @author Cheng Zhujiang
 * @date 2017/9/23
 */
public class ProxyTest {

    public static final int CIRCLE = 30000000;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NotFoundException, CannotCompileException {
        IDBQuery d;

        long begin = System.currentTimeMillis();
        d = createJdkProxy();
        System.out.println("createJdkProxy:" + (System.currentTimeMillis() - begin));
        System.out.println("JdkProxy class:" + d.getClass().getName());
        begin = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            d.request();
        }
        System.out.println("callJdkProxy:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        d = createCglibProxy(); // 测试CGLIB动态代理
        System.out.println("createCglibProxy:" + (System.currentTimeMillis() - begin));
        System.out.println("CglibProxy class:" + d.getClass().getName());
        begin = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            d.request();
        }
        System.out.println("callCglibProxy:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        d = createJavassistDynProxy();
        System.out.println("createJavassistDynProxy:" + (System.currentTimeMillis() - begin));
        System.out.println("JavassistDynProxy class:" + d.getClass().getName());
        begin = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            d.request();
        }
        System.out.println("callJavassistDynProxy:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        d = createJavassistBytecodeDynamicProxy();
        System.out.println("createJavassistBytecodeDynamicProxy:" + (System.currentTimeMillis() - begin));
        System.out.println("JavassistBytecodeDynamicProxy class:" + d.getClass().getName());
        begin = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            d.request();
        }
        System.out.println("callJavassistBytecodeDynamicProxy:" + (System.currentTimeMillis() - begin));
    }

    public static IDBQuery createJdkProxy() {
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[] { IDBQuery.class }, new JdkDbQueryHandler());
        return jdkProxy;
    }

    public static IDBQuery createCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibDbQueryInterceptor()); // 指定切入器，定义代理类逻辑
        enhancer.setInterfaces(new Class[] { IDBQuery.class }); // 指定实现的接口
        IDBQuery cglibProxy = (IDBQuery) enhancer.create(); // 生成代理类的实例
        return cglibProxy;
    }

    // 使用代理工厂创建
    public static IDBQuery createJavassistDynProxy() throws IllegalAccessException, InstantiationException {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[] { IDBQuery.class });
        Class proxyClass = proxyFactory.createClass();
        IDBQuery javassistProxy = (IDBQuery) proxyClass.newInstance();
        ((ProxyObject)javassistProxy).setHandler(new JavassistDynDbQueryHandler());
        return javassistProxy;
    }

    // 使用动态代码创建
    public static IDBQuery createJavassistBytecodeDynamicProxy() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool mPool = new ClassPool();
        CtClass mCtc = mPool.makeClass(IDBQuery.class.getName() + "Javaassist-BytecodeProxy");
        // 需要实现的接口
        mCtc.addInterface(mPool.get(IDBQuery.class.getName())); // TODO 报错
        // 添加构造函数
        mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
        // 添加类的字段信息，使用动态Java代码
        mCtc.addField(CtField.make("public " + IDBQuery.class.getName() + " real;", mCtc));
        String dbQueryName = DBQuery.class.getName();
        // 添加方法，这里使用动态Java代码指定内部逻辑
        mCtc.addMethod(CtNewMethod.make("public String request() { if (" +
                        "real == null) real = new " + dbQueryName + "(); return real.request(); }"
                , mCtc));
        // 基于以上信息，生成动态类
        Class pc = mCtc.toClass();
        // 生成动态类的实例
        return (IDBQuery) pc.newInstance();
    }
}
