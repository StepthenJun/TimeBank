package com.example.client.mapper;



import com.example.client.domain.Event;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.client.domain.Event;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86187
* @description 针对表【event】的数据库操作Mapper
* @createDate 2024-02-15 20:11:24
* @Entity com/client.domain.Event
*/
@Mapper
public interface EventMapper extends BaseMapper<Event> {

}




