package com.example.api.domain;

import lombok.Data;

/**
 * @program: ledger
 * @ClassName: LedgerVo
 * @description:
 * @author: kai
 * @create: 2024-02-21 20:42
 */
@Data
public class LedgerVo {
    private long height;
    private long transactionNum;
    private String currentBlockHash;
    private String previousBlockHash;

    public LedgerVo(long height, long transactionNum, String currentBlockHash, String previousBlockHash) {
        this.height = height;
        this.transactionNum = transactionNum;
        this.currentBlockHash = currentBlockHash;
        this.previousBlockHash = previousBlockHash;
    }
}
