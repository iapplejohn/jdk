package com.jemmy.reflect.sample;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhujiang.cheng
 * @since 2021/6/11
 */
@Getter
@Setter
public class TaskSendResult extends BaseModel {

    private String topic;

    private String status;

    private String count;
}
