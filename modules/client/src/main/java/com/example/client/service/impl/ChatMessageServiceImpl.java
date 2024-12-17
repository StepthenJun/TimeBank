package com.example.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.ChatMessage;
import com.example.client.service.ChatMessageService;
import com.example.client.mapper.ChatMessageMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【chat_message】的数据库操作Service实现
* @createDate 2024-12-18 00:12:57
*/
@RequiredArgsConstructor
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage>
    implements ChatMessageService{

  private final ChatMessageMapper chatMessageMapper;

  @Override
  public void sendMessage(ChatMessage message) {
    // 存储消息到数据库
    chatMessageMapper.insert(message);
  }

  @Override
  public List<ChatMessage> getChatHistory(Integer userId, Integer friendId) {
    // 查询两者之间的聊天记录
    return chatMessageMapper.selectList(
        new QueryWrapper<ChatMessage>().lambda()
            .eq(ChatMessage::getSenderId, userId)
            .eq(ChatMessage::getReceiverId, friendId)
            .or()
            .eq(ChatMessage::getSenderId, friendId)
            .eq(ChatMessage::getReceiverId, userId)
            .orderByAsc(ChatMessage::getTimestamp)
    );
  }
}




