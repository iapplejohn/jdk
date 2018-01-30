/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Test.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/28 12:09
 * Description: 
 */
package com.jemmy.serialize;

import java.io.*;

/**
 * <pre>
 * Test
 *
 * @author Cheng Zhujiang
 * @date 2017/10/28
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Address address = new Address();
        address.setDetail("431dfa");
        Person person = new Person();
        person.setName("good");
        person.setAddress(address);

        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("D://person.obj")));
        oos.writeObject(person);
        oos.close();
    }

}
