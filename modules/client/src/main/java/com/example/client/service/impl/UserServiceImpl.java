package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.User;
import com.example.client.service.UserService;
import com.example.client.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-11-25 15:20:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




