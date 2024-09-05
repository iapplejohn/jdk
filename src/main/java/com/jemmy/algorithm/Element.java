package com.jemmy.algorithm;

import lombok.Getter;

/**
 * @author zhujiang.cheng
 * @since 2022/6/14
 */
@Getter
public class Element {

    protected String peer;

    protected int weight;

    public Element(String peer, int weight) {
        this.peer = peer;
        this.weight = weight;
    }
}
