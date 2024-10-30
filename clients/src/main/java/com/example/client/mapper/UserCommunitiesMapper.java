package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.UserCommunities;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 用户与社区的关系表(UserCommunities)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface UserCommunitiesMapper extends BaseMapper<UserCommunities> {

}

