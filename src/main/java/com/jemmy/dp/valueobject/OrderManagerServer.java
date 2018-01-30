/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: OrderManagerServer.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/16 21:15
 * Description: 
 */
package com.jemmy.dp.valueobject;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * <pre>
 * OrderManagerServer
 *
 * @author Cheng Zhujiang
 * @date 2017/10/16
 */
public class OrderManagerServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // 注册RMI接口
            IOrderManager orderManager = new OrderManager(); // RMI远程对象
            Naming.bind("orderManager", orderManager);
            System.out.println("OrderManager is ready.");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            System.out.println("OrderManager Server failed: " + e);
        }
    }
}
