package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Follows;
import com.example.client.service.FollowsService;
import com.example.client.mapper.FollowsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【follows】的数据库操作Service实现
* @createDate 2024-02-19 11:40:15
*/
@Service
public class FollowsServiceImpl extends ServiceImpl<FollowsMapper, Follows>
    implements FollowsService{

}




