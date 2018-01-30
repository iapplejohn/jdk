/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: ConcreteObserver.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/28 18:28
 * Description: 
 */
package com.jemmy.dp.observer;

/**
 * <pre>
 * ConcreteObserver
 *
 * @author Cheng Zhujiang
 * @date 2017/9/28
 */
public class ConcreteObserver implements IObserver {

    @Override
    public void update(Event evt) {
        System.out.println("observer receives information");
    }
}
