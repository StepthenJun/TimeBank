package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Notifications;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 存储通知详细信息，包括定时发送功能(Notifications)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:59
 */
public interface NotificationsMapper extends BaseMapper<Notifications> {

}

