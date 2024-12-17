package com.example.client.mapper;

import com.example.client.domain.ChatMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86187
* @description 针对表【chat_message】的数据库操作Mapper
* @createDate 2024-12-18 00:12:57
* @Entity com.example.client.domain.ChatMessage
*/
@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {

}




