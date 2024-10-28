package com.example.core.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Grades;
@Mapper
/**
 * 存储年级信息(Grades)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface GradesMapper extends BaseMapper<Grades> {

}

