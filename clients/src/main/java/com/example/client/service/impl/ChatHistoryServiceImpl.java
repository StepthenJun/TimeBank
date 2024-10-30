package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.ChatHistory;
import com.example.client.service.ChatHistoryService;
import com.example.client.mapper.ChatHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【chat_history(存储用户之间的聊天记录)】的数据库操作Service实现
* @createDate 2024-10-30 14:44:56
*/
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory>
    implements ChatHistoryService{

}




