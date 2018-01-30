/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: RealData.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/3 12:58
 * Description: 
 */
package com.jemmy.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * RealData
 *
 * @author Cheng Zhujiang
 * @date 2017/8/3
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String para) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                // 这里使用sleep, 代替一个很慢的操作过程
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
