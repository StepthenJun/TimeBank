package com.example.client.mapper;

import com.example.client.domain.Event;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
* @author 86187
* @description 针对表【event】的数据库操作Mapper
* @createDate 2024-03-08 19:47:42
* @Entity com.example.client.domain.Event
*/
@Mapper
public interface EventMapper extends BaseMapper<Event> {

}




