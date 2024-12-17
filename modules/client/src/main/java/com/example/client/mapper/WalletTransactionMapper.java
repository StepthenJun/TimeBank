package com.example.client.mapper;

import com.example.client.domain.WalletTransaction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86187
* @description 针对表【wallet_transaction】的数据库操作Mapper
* @createDate 2024-12-18 02:57:47
* @Entity com.example.client.domain.WalletTransaction
*/
@Mapper
public interface WalletTransactionMapper extends BaseMapper<WalletTransaction> {

}




