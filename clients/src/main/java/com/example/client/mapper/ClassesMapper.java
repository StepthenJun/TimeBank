package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Classes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 存储班级信息(Classes)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface ClassesMapper extends BaseMapper<Classes> {

}

