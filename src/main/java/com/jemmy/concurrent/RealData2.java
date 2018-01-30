/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: RealData2.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/22 8:08
 * Description: 
 */
package com.jemmy.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * RealData2
 *
 * @author Cheng Zhujiang
 * @date 2017/8/22
 */
public class RealData2 implements Callable<String> {

    private String para;

    public RealData2(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
