/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: DemoMBean.java
 * Author:   Cheng Zhujiang
 * Date:     2017/11/3 15:54
 * Description: 
 */
package com.jemmy.jmx;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <pre>
 * DemoMBean
 *
 * @author Cheng Zhujiang
 * @date 2017/11/3
 */
public interface DemoMBean {

    AtomicLong getInvokeCount();

}
