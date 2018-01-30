/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: OrderManagerClient.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/16 21:17
 * Description: 
 */
package com.jemmy.dp.valueobject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * <pre>
 * OrderManagerClient
 *
 * @author Cheng Zhujiang
 * @date 2017/10/16
 */
public class OrderManagerClient {

    public static void main(String[] args) {
        try {
            IOrderManager orderManager = (IOrderManager)Naming.lookup("orderManager");
            long begin = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                orderManager.getOrder(i); // Value Object模式
            }
            System.out.println("getOrder spend:" + (System.currentTimeMillis() - begin));

            begin = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                orderManager.getClientName(i);
                orderManager.getNumber(i);
                orderManager.getProdName(i);
            }
            System.out.println("3 Method call spend:" + (System.currentTimeMillis() - begin));
            System.out.println(orderManager.getOrder(0).getClientName());
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.out.println("OrderManager exception: " + e);
        }
    }
}
