package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Tags;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 标签信息表(Tags)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface TagsMapper extends BaseMapper<Tags> {

}

