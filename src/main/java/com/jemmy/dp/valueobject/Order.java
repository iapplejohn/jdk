/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Order.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/16 21:08
 * Description: 
 */
package com.jemmy.dp.valueobject;

import java.io.Serializable;

/**
 * <pre>
 * Order
 *
 * @author Cheng Zhujiang
 * @date 2017/10/16
 */
public class Order implements Serializable {

    private String clientName;

    private int number;

    private String productName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
