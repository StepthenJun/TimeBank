package com.example.client.service;

import com.example.client.domain.Event;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.client.domain.Event;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
* @author 86187
* @description 针对表【event】的数据库操作Service
* @createDate 2024-02-15 20:11:24
*/
public interface EventService extends IService<Event> {


    List<Event> getEventListByRules(String executionTime, String  publishTime,Boolean query) throws ParseException;


    String getEventNameById(Long eventId);
}
