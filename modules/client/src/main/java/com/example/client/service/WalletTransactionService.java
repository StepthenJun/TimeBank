package com.example.client.service;

import com.example.client.domain.WalletTransaction;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
* @author 86187
* @description 针对表【wallet_transaction】的数据库操作Service
* @createDate 2024-12-18 02:57:47
*/
public interface WalletTransactionService extends IService<WalletTransaction> {
  void recordTransaction(Integer userId, Integer orderId, String transactionType, Double amount, Double balance);
  List<WalletTransaction> getTransactions(Integer userId);
}
