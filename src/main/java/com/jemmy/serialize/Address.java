/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Address.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/28 12:08
 * Description: 
 */
package com.jemmy.serialize;

import java.io.Serializable;

/**
 * <pre>
 * Address
 *
 * @author Cheng Zhujiang
 * @date 2017/10/28
 */
public class Address implements Serializable {

    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
