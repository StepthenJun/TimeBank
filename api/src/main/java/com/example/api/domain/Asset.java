package com.example.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: TimeBank
 * @ClassName: Asset
 * @description:
 * @author: kai
 * @create: 2024-03-09 16:56
 */
@Data
@AllArgsConstructor
public class Asset {
    private Long assetId;
    private Integer appraisedValue;
    private Long fromAssetId;
    private Integer exchangeValue;

}
