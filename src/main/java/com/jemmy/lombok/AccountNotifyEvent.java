package com.jemmy.lombok;

import com.google.common.base.Preconditions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 账户通知事件
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AccountNotifyEvent extends Event {

    /**
     * 业务类型
     */
    private String bizType;
    /**
     * 业务主键
     */
    private String bizKey;

    /**
     * 客户id
     */
    private String clientId;

    /**
     * 关联id
     */
    private String refId;
    /**
     * 下一步事件
     */
    private AccountNotifyEvent next;
    /**
     * 是否为历史记录
     */
    private Boolean isHis;


    @Override
    protected String bizKey() {
        return "AccountNotifyEvent";
    }

    @Override
    public AccountNotifyEvent next() {
        return next;
    }

    @Override
    public String topic() {
        return "account_notify_topic";
    }

    @Override
    protected String status() {
        return null;
    }

    @Override
    protected void check() {
        Preconditions.checkArgument(StringUtils.isNotBlank(bizType), "bizType is blank.");
        Preconditions.checkArgument(StringUtils.isNotBlank(bizKey), "bizKey is blank.");
    }
}
