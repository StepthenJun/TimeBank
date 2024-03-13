package com.example.client.mapper;

import com.example.client.domain.Notification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86187
* @description 针对表【notification】的数据库操作Mapper
* @createDate 2024-03-10 10:49:16
* @Entity com.example.client.domain.Notification
*/
@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {

}




