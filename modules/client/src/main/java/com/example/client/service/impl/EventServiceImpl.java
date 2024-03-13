package com.example.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.client.service.CommentService;
import com.example.client.service.EventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Event;
import com.example.client.mapper.EventMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @author 86187
* @description 针对表【event】的数据库操作Service实现
* @createDate 2024-02-15 20:11:24
*/
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event>
    implements EventService {
    /**
     * 根据条件进行返回结果
     * @param executionTime
     * @param publishTime
     * @return
     * @throws ParseException
     */
    @Override
    public List<Event> getEventListByRules(String executionTime, String publishTime,Boolean query) throws ParseException {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 如果传入的参数不为空，则将日期字符串转换为 Date 对象，并添加查询条件
        if (executionTime != null && !executionTime.isEmpty()) {
            Date executionDate = sdf.parse(executionTime);
            queryWrapper.apply("DATE(execution_time) = DATE('" + sdf.format(executionDate) + "')");
        }
        if (publishTime != null && !publishTime.isEmpty()) {
            Date publishDate = sdf.parse(publishTime);
            queryWrapper.apply("DATE(create_time) = DATE('" + sdf.format(publishDate) + "')");
        }

        // 如果 query 为ture，则添加 needNum 的查询条件
        if (query) {
            queryWrapper.orderByDesc("need_num");
        }

        return list(queryWrapper);
    }

    @Override
    public String getEventNameById(Long eventId) {
        return getById(eventId).getName();
    }
}




