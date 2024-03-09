package com.example.api.domain;
import lombok.Data;

/**
 * @program: ledger
 * @ClassName: UpdateAssetDto
 * @description:
 * @author: kai
 * @create: 2024-02-21 20:06
 */
@Data
public class UpdateAssetDto {
    /**
     * 交易金额
     */
    private int updateValue;
    /**
     * 资产ID（用户id）
     */
    private String assetId;
    /**
     * 交易对象id
     */
    private String fromAssetId;
}
