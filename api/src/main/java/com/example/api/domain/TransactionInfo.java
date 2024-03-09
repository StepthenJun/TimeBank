package com.example.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: TimeBank
 * @ClassName: TransactionInfo
 * @description:
 * @author: kai
 * @create: 2024-03-09 17:00
 */
@Data
@AllArgsConstructor
public class TransactionInfo {
    /**
     * 交易所在块号
     */
    private  String blockNum;
    /**
     * 前块hash（无法获得交易所在的块hash）
     */
    private String  previousBlockHash;
}
