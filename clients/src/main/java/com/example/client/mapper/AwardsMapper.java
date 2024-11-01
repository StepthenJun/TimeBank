package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Awards;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 用户获奖信息表(Awards)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface AwardsMapper extends BaseMapper<Awards> {

}

