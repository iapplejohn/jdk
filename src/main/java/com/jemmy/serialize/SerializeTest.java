/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: SerializeTest.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/28 12:09
 * Description: 
 */
package com.jemmy.serialize;

import java.io.*;

/**
 * <pre>
 * SerializeTest
 *
 * @author Cheng Zhujiang
 * @date 2017/10/28
 */
public class SerializeTest {

    public static void main(String[] args) throws IOException {
        Address address = new Address();
        address.setDetail("431dfa");
        Person person = new Person();
        person.setName("good");
        person.setAddress(address);
        person.setGenderEnum(GenderEnum.MALE);

        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("person.obj")));
        oos.writeObject(person);
        oos.close();
    }

}
