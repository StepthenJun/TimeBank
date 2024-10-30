package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Majors;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 存储专业信息(Majors)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:59
 */
public interface MajorsMapper extends BaseMapper<Majors> {

}

