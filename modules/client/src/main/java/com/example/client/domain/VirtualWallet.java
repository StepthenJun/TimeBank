package com.example.client.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName virtual_wallet
 */
@TableName(value ="virtual_wallet")
@Data
public class VirtualWallet implements Serializable {
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
    @TableField(value = "balance")
    private Double balance;

    /**
     * 
     */
    @TableField(value = "card_type")
    private String cardType;

    /**
     * 
     */
    @TableField(value = "holder_name")
    private String holderName;

    /**
     * 
     */
    @TableField(value = "payment_password")
    private String paymentPassword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}