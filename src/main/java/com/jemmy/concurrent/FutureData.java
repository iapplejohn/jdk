/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: FutureData.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/3 12:47
 * Description: 
 */
package com.jemmy.concurrent;

/**
 * FutureData
 *
 * @author Cheng Zhujiang
 * @date 2017/8/3
 */
public class FutureData implements Data {

    protected RealData realData = null; // FutureData是RealData的包装
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }

        this.realData = realData;
        isReady = true;
        notifyAll(); // RealData已经被注入，通知getResult()
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait(); // 一直等待，直到RealData被注入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result; // 由RealData实现
    }
}
