package com.example.client.service;

import com.example.client.domain.ChatMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
* @author 86187
* @description 针对表【chat_message】的数据库操作Service
* @createDate 2024-12-18 00:12:57
*/
public interface ChatMessageService extends IService<ChatMessage> {
  // 发送消息并存储聊天记录
  void sendMessage(ChatMessage message);

  // 获取聊天记录
  List<ChatMessage> getChatHistory(Integer userId, Integer friendId);
}
