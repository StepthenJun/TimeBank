package com.example.client.mapper;

import com.example.client.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86187
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-11-27 19:57:46
* @Entity com.example.client.domain.User
*/
@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

}




