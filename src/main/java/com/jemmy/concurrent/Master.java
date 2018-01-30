/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Master.java
 * Author:   Cheng Zhujiang
 * Date:     2017/8/22 8:58
 * Description: 
 */
package com.jemmy.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <pre>
 * Master
 *
 * @author Cheng Zhujiang
 * @date 2017/8/22
 */
public class Master {

    // 任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<Object>();

    // Worker进程队列
    protected Map<String, Thread> threadMap = new HashMap<String, Thread>();

    // 子任务处理结果集

}
