package com.jemmy.serialize;

/**
 * @author zhujiang.cheng
 * @since 2020/6/1
 */
public class GsonTest {

    public static void main(String[] args) {

    }

    /**
     * 泛型测试
     *
     * @return
     */
    private static Wrapper<Person> buildGeneric() {
        Wrapper<Person> wrapper = new Wrapper<>();
        wrapper.setName("john");
        wrapper.setT(buildPerson());
        return wrapper;
    }

    private static Person buildPerson() {
        Address address = new Address();
        address.setDetail("431dfa");
        Person person = new Person();
        person.setName("good");
        person.setAddress(address);
        return person;
    }
}
