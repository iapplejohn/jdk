/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: ConcreteSubject.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/28 18:25
 * Description: 
 */
package com.jemmy.dp.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * ConcreteSubject
 *
 * @author Cheng Zhujiang
 * @date 2017/9/28
 */
public class ConcreteSubject implements ISubject {

    List<IObserver> observers = new LinkedList<>();

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void inform() {
        Event event = new Event();
        for (IObserver observer : observers) {
            observer.update(event);
        }
    }
}
