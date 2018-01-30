/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: Main.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/19 12:17
 * Description: 
 */
package com.jemmy.dp.proxy;

/**
 * <pre>
 * Main
 *
 * @author Cheng Zhujiang
 * @date 2017/9/19
 */
public class Main {
    public static void main(String[] args) {
        IDBQuery q = new DBQueryProxy(); // 使用代理
        q.request(); // 在真正使用时才创建真实对象
    }
}
