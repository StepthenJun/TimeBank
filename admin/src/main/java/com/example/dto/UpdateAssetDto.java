package com.example.dto;
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
    private int updateValue;
    private String assetId;
    private String fromAssetId;
}
