/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: DemoImpl.java
 * Author:   Cheng Zhujiang
 * Date:     2017/11/3 15:55
 * Description: 
 */
package com.jemmy.jmx;

import javax.management.InstanceAlreadyExistsException;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <pre>
 * DemoImpl
 *
 * @author Cheng Zhujiang
 * @date 2017/11/3
 */
public class DemoImpl implements DemoMBean {

    public final static String DEFAULT_OBJECT_NAME_PREFIX = "com.redcreen.demo:type=demo";

    private AtomicLong atomicL = new AtomicLong(0);

    {
        register("com.jemmy.demo:type=demo", this);
    }

    @Override
    public AtomicLong getInvokeCount() {
        atomicL.incrementAndGet();
        return atomicL;
    }

    public static ObjectName register(String name, Object mBean) {

        try {
            ObjectName objectName = new ObjectName(name);
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            try {
                mBeanServer.registerMBean(mBean, objectName);
            } catch (InstanceAlreadyExistsException e) {
                mBeanServer.unregisterMBean(objectName);
                mBeanServer.registerMBean(mBean, objectName);
            }

            return objectName;
        } catch (JMException e) {
            throw new IllegalArgumentException(name, e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DemoImpl impl = new DemoImpl();
        TimeUnit.SECONDS.sleep(70);
    }
}
