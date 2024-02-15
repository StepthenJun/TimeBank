package com.example.client.service.impl;

import com.example.client.service.EventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Event;
import com.example.client.mapper.EventMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【event】的数据库操作Service实现
* @createDate 2024-02-15 20:11:24
*/
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event>
    implements EventService {

}




