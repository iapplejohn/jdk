/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Person.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/28 12:08
 * Description: 
 */
package com.jemmy.serialize;

import java.io.Serializable;

/**
 * <pre>
 * Person
 *
 * @author Cheng Zhujiang
 * @date 2017/10/28
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 7691306818026662116L;

    private String id;

    private String name;

    private Address address;

    private GenderEnum genderEnum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }
}
