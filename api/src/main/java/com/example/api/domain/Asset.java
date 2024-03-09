package com.example.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: TimeBank
 * @ClassName: Asset
 * @description:存储在区块上的交易
 * @author: kai
 * @create: 2024-03-09 16:56
 */
@Data
@AllArgsConstructor
public class Asset {
    /**
     * 资产ID（用户id）
     */
    private Long assetId;
    /**
     * 当前用户余额
     */
    private Integer appraisedValue;
    /**
     * 交易对象ID
     */
    private Long fromAssetId;
    /**
     * 交易金额
     */
    private Integer exchangeValue;

}
