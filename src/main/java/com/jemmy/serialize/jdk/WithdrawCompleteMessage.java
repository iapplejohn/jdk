package com.jemmy.serialize.jdk;

import java.io.Serializable;
import lombok.Data;

@Data
public class WithdrawCompleteMessage implements Serializable {

    /**
     * clientId
     */
    private String clientId;

    /**
     * 提现ID
     */
    private String withdrawId;

    /**
     * 提现银行卡UID
     */
    private String beneficiaryUid;

    /**
     * 提现状态，参考Account的PayoutStatusEnum
     */
    private Integer withdrawStatus;

    /**
     * 提现类型
     */
    private Integer withdrawType;

    /**
     * 提现类型
     */
    private String convertWithdrawType;

//    private String newField;
}