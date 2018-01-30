/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: ISubject.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/28 18:23
 * Description: 
 */
package com.jemmy.dp.observer;

/**
 * <pre>
 * ISubject
 *
 * @author Cheng Zhujiang
 * @date 2017/9/28
 */
public interface ISubject {

    // 添加观察者
    void attach(IObserver observer);

    // 删除观察者
    void detach(IObserver observer);

    // 通知所有观察者
    void inform();
}
