package com.example.client.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 存储用户之间的聊天记录
 * @TableName chat_history
 */
@TableName(value ="chat_history")
@Data
public class ChatHistory implements Serializable {
    /**
     * 聊天记录ID
     */
    @TableId(value = "chat_id", type = IdType.AUTO)
    private Integer chatId;

    /**
     * 发送者用户ID
     */
    @TableField(value = "sender_id")
    private Integer senderId;

    /**
     * 接收者用户ID
     */
    @TableField(value = "receiver_id")
    private Integer receiverId;

    /**
     * 消息内容
     */
    @TableField(value = "message")
    private String message;

    /**
     * 消息发送时间
     */
    @TableField(value = "message_time")
    private Date messageTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}