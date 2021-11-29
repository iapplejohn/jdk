package com.jemmy.groovy;

import groovy.lang.GroovyClassLoader;
import java.io.File;

/**
 * @author zhujiang.cheng
 * @since 2021/6/2
 */
public class InvokeGroovy2 {

    public static void main(String[] args) {
        ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {
            //从文件中读取，将实现IFoo接口的groclass Foo implements IFoo {public Object run(Object foo) {return 2+2>1}}ovy类写在一个groovy文件中
            Class groovyClass = groovyCl.parseClass(new File("src/main/resources/Foo.groovy"));
            System.out.println("groovyClass = " + groovyClass);

            Class groovyClass2 = groovyCl.parseClass(new File("src/main/resources/Foo2.groovy"));
            System.out.println("groovyClass2 = " + groovyClass2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
