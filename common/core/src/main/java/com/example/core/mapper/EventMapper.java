package com.example.core.mapper;
import com.example.core.domain.pojos.Event;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
/**
 * (Event)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:49
 */
public interface EventMapper extends BaseMapper<Event> {

}
