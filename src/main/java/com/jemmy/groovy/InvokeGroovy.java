package com.jemmy.groovy;

import groovy.lang.GroovyClassLoader;


public class InvokeGroovy {
    public static void main(String[] args) {
        ClassLoader cl = InvokeGroovy.class.getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {
            //从文件中读取，将实现IFoo接口的groclass Foo implements IFoo {public Object run(Object foo) {return 2+2>1}}ovy类写在一个groovy文件中
            //Class groovyClass = groovyCl.parseClass(new File("./src/sample/Foo.groovy"));
            //直接使用Groovy字符串,也可以获得正确结果
//            Class groovyInt = groovyCl.parseClass("package com.jemmy.groovy; public interface IFoo { Object run(Object foo); }");

            Class groovyClass = groovyCl.parseClass("package com.jemmy.groovy; public class Foo implements IFoo {public Object run(Object foo) {return 2+2>1}}");//这个返回true
            IFoo foo = (IFoo) groovyClass.newInstance();
            System.out.println(foo.run(2));

            Class groovyClass2 = groovyCl.parseClass("package com.jemmy.groovy; class Foo implements IFoo {public Object run(Object foo) {return 2+2>1}}");//这个返回true
            System.out.println("groovyClass2 = " + groovyClass2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
