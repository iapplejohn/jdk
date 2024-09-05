package com.jemmy.lombok;

import lombok.Data;

@Data
public abstract class Event {

    public Event() {
    }

    /**
     * 业务主键
     */
    protected abstract String bizKey();

    /**
     * 要触发的下一个事件
     */
    protected abstract Event next();

    /**
     * 事件主题
     * @return
     */
    protected abstract String topic();

    /**
     * 事件完成后对应的状态
     * @return
     */
    protected abstract String status();

    /**
     * 校验参数是否正确
     */
    protected abstract void check();
}
