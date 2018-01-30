/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Student.java
 * Author:   Cheng Zhujiang
 * Date:     2017/12/18 19:07
 * Description: 
 */
package com.jemmy.tune.ch03;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Student
 *
 * @author Cheng Zhujiang
 * @date 2017/12/18
 */
public class Student implements Cloneable {

    private int id;
    private String name;
    private ArrayList<String> courses;

    public Student() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Student Constructor called");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public Student newInstance() { // 使用clone创建对象
        try {
            return (Student) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // deep copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student stu = (Student) super.clone();
        ArrayList<String> list1 = stu.getCourses();
        ArrayList<String> list2 = new ArrayList<>();
        for (String course : list1) {
            list2.add(course);
        }
        stu.setCourses(list2);
        return stu;
    }
}
