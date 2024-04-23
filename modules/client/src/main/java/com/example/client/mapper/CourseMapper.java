package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.client.domain.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: TimeBank
 * @ClassName: CourseMapper
 * @description:
 * @author: kai
 * @create: 2024-04-07 17:04
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

}
