package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 存储用户之间的聊天记录(ChatHistory)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatHistory  {
    //聊天记录ID@TableId
    private Integer chatId;

    //发送者用户ID
    private Integer senderId;
    //接收者用户ID
    private Integer receiverId;
    //消息内容
    private String message;
    //消息发送时间
    private Date messageTime;



}

