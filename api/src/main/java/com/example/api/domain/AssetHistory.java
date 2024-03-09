package com.example.api.domain;
import lombok.Data;

import java.time.Instant;
/**
 * @program: TimeBank
 * @ClassName: AssetHistory
 * @description:
 * @author: kai
 * @create: 2024-03-09 16:47
 */
@Data
public class AssetHistory {
   private  Instant dealTime;
   private  String txId;
   private  boolean del;
   private  Asset asset;
}
