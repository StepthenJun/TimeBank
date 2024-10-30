package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Colleges;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 存储学院信息(Colleges)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface CollegesMapper extends BaseMapper<Colleges> {

}

