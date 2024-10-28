package com.example.core.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.UserTags;
@Mapper
/**
 * 用户与标签的关系表(UserTags)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:59
 */
public interface UserTagsMapper extends BaseMapper<UserTags> {

}

