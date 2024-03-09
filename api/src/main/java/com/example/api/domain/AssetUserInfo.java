package com.example.api.domain;

import lombok.Data;

/**
 * @program: TimeBank
 * @ClassName: AssetUserInfo
 * @description:
 * @author: kai
 * @create: 2024-03-09 15:56
 */
@Data
public class AssetUserInfo {
    /**
     * 资产ID（用户id）
     */
    private Long assetId;
    /**
     * 当前用户余额
     */
    private Integer appraisedValue;
}
