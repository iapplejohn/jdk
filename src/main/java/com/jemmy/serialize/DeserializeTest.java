package com.jemmy.serialize;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;

/**
 * @author zhujiang.cheng
 * @since 2020/3/2
 */
public class DeserializeTest {

    public static void main(String[] args) throws IOException {
//        byte[] bytes = toBytes();
//        System.out.println(bytes);
        test2();
    }

    private static void test1() {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream("person.obj"))) {
            ObjectInputStream ois = new ObjectInputStream(input);
            Person person = (Person) ois.readObject();
            System.out.println("person: " + person);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void test2() {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream("servers.db"))) {
            ObjectInputStream ois = new ObjectInputStream(input);
            Object obj = ois.readObject();
            System.out.println("obj: " + obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream("person.obj"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("person.txt"), "utf-8"))
        ) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int count = input.read(buffer, 0, 512);
            while (count > -1) {
                baos.write(buffer, 0, count);
                count = input.read(buffer);
            }

            System.out.println(new String(baos.toByteArray()));
            writer.write(new String(baos.toByteArray()));
            writer.write('\n');
            writer.write("---3121 fabulous----");
            writer.write('\n');
            writer.write("---314321eu-数据同步失败 fantastic----");
            writer.write('\n');
            writer.write("---3141 brilliant----");
            writer.write('\n');
            writer.write(new String(baos.toByteArray()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] toBytes() throws IOException {
        BufferedInputStream input = null;
        ByteArrayOutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream("person.obj"));
            output = new ByteArrayOutputStream();
            int count;
            byte[] buffer = new byte[1024];
            while ((count = input.read(buffer)) > 0) {
                output.write(buffer, 0, count);
            }
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }

        return output.toByteArray();
    }
}
