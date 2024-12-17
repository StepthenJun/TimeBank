package com.example.client.service;

import com.example.client.domain.VirtualWallet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86187
* @description 针对表【virtual_wallet】的数据库操作Service
* @createDate 2024-12-18 02:36:24
*/
public interface VirtualWalletService extends IService<VirtualWallet> {
  VirtualWallet getWalletByUserId(Integer userId);
  void deductBalance(Integer userId, Double amount, String password);
  void refundBalance(Integer userId, Double amount);
}
