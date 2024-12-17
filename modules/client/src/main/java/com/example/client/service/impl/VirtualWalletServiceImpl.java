package com.example.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.VirtualWallet;
import com.example.client.service.VirtualWalletService;
import com.example.client.mapper.VirtualWalletMapper;
import com.example.client.service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【virtual_wallet】的数据库操作Service实现
* @createDate 2024-12-18 02:36:24
*/
@Service
@RequiredArgsConstructor
public class VirtualWalletServiceImpl extends ServiceImpl<VirtualWalletMapper, VirtualWallet>
    implements VirtualWalletService{

  private final VirtualWalletMapper walletMapper;
  private final WalletTransactionService walletTransactionService;

  @Override
  public VirtualWallet getWalletByUserId(Integer userId) {
    return walletMapper.selectById(userId);
  }

  @Override
  public void deductBalance(Integer userId, Double amount, String password) {
    VirtualWallet wallet = walletMapper.selectById(userId);
    if (!wallet.getPaymentPassword().equals(password)) {
      throw new RuntimeException("支付密码错误");
    }
    if (wallet.getBalance() < amount) {
      throw new RuntimeException("余额不足");
    }
    double newBalance = wallet.getBalance() - amount;
    LambdaUpdateWrapper<VirtualWallet> wrapper = new LambdaUpdateWrapper<>();
    wrapper.eq(VirtualWallet::getUserId,newBalance);
    boolean update = update(wrapper);
    if (!update){
      throw new RuntimeException("失败");
    }
    // 记录交易流水
    walletTransactionService.recordTransaction(userId, null, "支付", amount, newBalance);
  }

  @Override
  public void refundBalance(Integer userId, Double amount) {
    VirtualWallet wallet = walletMapper.selectById(userId);
    LambdaUpdateWrapper<VirtualWallet> wrapper = new LambdaUpdateWrapper<>();
    double newBalance = wallet.getBalance() - amount;
    wrapper.eq(VirtualWallet::getUserId,newBalance);
    boolean update = update(wrapper);
    if (!update){
      throw new RuntimeException("失败");
    }
    walletTransactionService.recordTransaction(userId, null, "退款", amount, newBalance);

  }
}




