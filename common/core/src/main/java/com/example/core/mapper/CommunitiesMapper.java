package com.example.core.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Communities;
@Mapper
/**
 * 社区信息表(Communities)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:58
 */
public interface CommunitiesMapper extends BaseMapper<Communities> {

}

