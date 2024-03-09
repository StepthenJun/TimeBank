package com.example.api.domain;
import lombok.Data;

import java.time.Instant;
/**
 * @program: TimeBank
 * @ClassName: AssetHistory
 * @description:一项交易的交易信息
 * @author: kai
 * @create: 2024-03-09 16:47
 */
@Data
public class AssetHistory {
   /**
    *交易时间
    */
   private  Instant dealTime;
   /**
    *区块链上的交易Id，可根据Id查询区块信息
    */
   private  String txId;
   /**
     *是否交易删除（默认为false）
     */
   private  boolean del;
   private  Asset asset;
}
