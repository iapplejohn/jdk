package com.jemmy.serialize;

/**
 * @author zhujiang.cheng
 * @since 2020/6/1
 */
public class Wrapper<T> {

    private T t;

    private String name;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
