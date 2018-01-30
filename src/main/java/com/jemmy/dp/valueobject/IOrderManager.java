/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: IOrderManager.java
 * Author:   Cheng Zhujiang
 * Date:     2017/10/16 21:03
 * Description: 
 */
package com.jemmy.dp.valueobject;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <pre>
 * IOrderManager
 *
 * @author Cheng Zhujiang
 * @date 2017/10/16
 */
public interface IOrderManager extends Remote {

    Order getOrder(int id) throws RemoteException;

    String getClientName(int id) throws RemoteException;

    String getProdName(int id) throws RemoteException;

    int getNumber(int id) throws RemoteException;

}
