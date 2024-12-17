package com.example.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.WalletTransaction;
import com.example.client.service.WalletTransactionService;
import com.example.client.mapper.WalletTransactionMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【wallet_transaction】的数据库操作Service实现
* @createDate 2024-12-18 02:57:47
*/
@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl extends ServiceImpl<WalletTransactionMapper, WalletTransaction>
    implements WalletTransactionService{

  private final WalletTransactionMapper transactionMapper;

  @Override
  public void recordTransaction(Integer userId, Integer orderId, String transactionType, Double amount, Double balance) {
    WalletTransaction transaction = new WalletTransaction();
    transaction.setUserId(userId);
    transaction.setOrderId(orderId);
    transaction.setTransactionType(transactionType);
    transaction.setAmount(amount);
    transaction.setBalance(balance);
    transactionMapper.insert(transaction);
  }

  @Override
  public List<WalletTransaction> getTransactions(Integer userId) {
    return transactionMapper.selectList(
        new LambdaQueryWrapper<WalletTransaction>().eq(WalletTransaction::getUserId,userId));
  }
}




