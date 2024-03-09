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
    private  String blockNum;
    private String  previousBlockHash;
}
