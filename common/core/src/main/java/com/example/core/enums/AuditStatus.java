package com.example.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参与者的状态
 */
@Getter
@AllArgsConstructor
public enum AuditStatus {
    /**
     * 未审核
     */
    UNAUDIT("1", "已申请"),
    /**
     * 审核通过
     */
    ACCEPT("2", "已接受"),
    /**
     * 完成
     */
    FINISH("3","已完成");

    private final String code;
    private final String info;
}
