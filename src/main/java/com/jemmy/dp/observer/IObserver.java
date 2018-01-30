/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: IObserver.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/28 18:24
 * Description: 
 */
package com.jemmy.dp.observer;

/**
 * <pre>
 * IObserver
 *
 * @author Cheng Zhujiang
 * @date 2017/9/28
 */
public interface IObserver {

    void update(Event evt);
}
