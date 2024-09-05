package com.jemmy.serialize.jdk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;

/**
 * @author zhujiang.cheng
 * @since 2023/7/22
 */
public class JdkSerializeTest {

    @Test
    public void serializeTest() throws IOException {
        Citizen citizen = new Citizen();
//        citizen.setAge(10);
        citizen.setName("John");

        byte[] bytes = JdkSerializer.serialize(citizen);
        Files.write(Paths.get("citizen.obj"), bytes);
    }

    @Test
    public void deserializeTest() throws IOException, ClassNotFoundException {
        byte[] bytes = Files.readAllBytes(Paths.get("citizen.obj"));

        Citizen citizen = (Citizen) JdkDeserializer.deserialize(bytes);
        System.out.println("citizen = " + citizen);
    }
}
