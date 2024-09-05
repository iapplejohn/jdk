package com.jemmy.serialize.jdk;

import java.io.Serializable;

/**
 * @author zhujiang.cheng
 * @since 2023/7/22
 */
public class Citizen implements Serializable {

//    private static final long serialVersionUID = 1L;

//    private int age;

    private String name;

    private String phone;

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
//        return "Citizen[age=" + age + ",name=" + name + "]";
        return "Citizen[name=" + name + "]";
    }
}
