/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: OrderManager.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/16 21:10
 * Description: 
 */
package com.jemmy.dp.valueobject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * <pre>
 * OrderManager
 *
 * @author Cheng Zhujiang
 * @date 2017/10/16
 */
public class OrderManager extends UnicastRemoteObject implements IOrderManager {

    private static final long serialVersionUID = 1L;

    protected OrderManager() throws RemoteException {
        super();
    }

    @Override
    public Order getOrder(int id) throws RemoteException {
        Order o = new Order();
        o.setClientName("billy");
        o.setNumber(20);
        o.setProductName("desk");
        return o;
    }

    @Override
    public String getClientName(int id) throws RemoteException {
        return "billy";
    }

    @Override
    public String getProdName(int id) throws RemoteException {
        return "desk";
    }

    @Override
    public int getNumber(int id) throws RemoteException {
        return 20;
    }
}
