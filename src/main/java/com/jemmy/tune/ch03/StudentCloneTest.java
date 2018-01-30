/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: StudentCloneTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/18 19:10
 * Description: 
 */
package com.jemmy.tune.ch03;

import java.util.ArrayList;

/**
 * <pre>
 * 3.5.11 使用clone代替new
 *
 * @author Cheng Zhujiang
 * @date 2017/12/18
 */
public class StudentCloneTest {

    public static void main(String[] args) {
        Student stu1 = new Student();
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        stu1.setId(1);
        stu1.setName("John");
        stu1.setCourses(courses);

        Student stu2 = stu1.newInstance(); // 使用clone方法生成对象
        stu2.setId(2);
        stu2.setName("Tracy");
        stu2.getCourses().add("Python"); // 修改了Vector的数据

        System.out.println("Stu1's name:" + stu1.getName());
        System.out.println("Stu2's name:" + stu2.getName());
        System.out.println(stu1.getCourses() == stu2.getCourses()); // 两个Student使用同一个List
    }
}
