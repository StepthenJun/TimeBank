package com.example.core.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.NotificationTargets;
@Mapper
/**
 * 定义通知的发送目标，如专业、班级或年级(NotificationTargets)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface NotificationTargetsMapper extends BaseMapper<NotificationTargets> {

}

