package com.example.core.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.NotificationTypes;
@Mapper
/**
 * 定义不同类型的通知(NotificationTypes)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:59
 */
public interface NotificationTypesMapper extends BaseMapper<NotificationTypes> {

}

