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
 * @TableName chat_message
 */
@TableName(value ="chat_message")
@Data
public class ChatMessage implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "sender_id")
    private Integer senderId;

    /**
     * 
     */
    @TableField(value = "receiver_id")
    private Integer receiverId;

    /**
     * 
     */
    @TableField(value = "message")
    private String message;

    /**
     * 
     */
    @TableField(value = "timestamp")
    private Date timestamp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}