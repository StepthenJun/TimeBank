package com.example.client.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName wallet_transaction
 */
@TableName(value ="wallet_transaction")
@Data
public class WalletTransaction implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     * 
     */
    @TableField(value = "transaction_type")
    private String transactionType;

    /**
     * 
     */
    @TableField(value = "amount")
    private Double amount;

    /**
     * 
     */
    @TableField(value = "balance")
    private Double balance;

    /**
     * 
     */
    @TableField(value = "transaction_time")
    private Date transactionTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}