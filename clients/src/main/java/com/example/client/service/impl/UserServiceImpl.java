package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.mapper.UserMapper;
import com.example.client.model.vo.UserVo;
import com.example.client.service.UserService;
import com.example.core.domain.pojos.User;

import com.example.core.util.BeanCopyUtils;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-10-28 20:22:32
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Override
  public UserVo getUserInfo(long userId) {
    User user = getById(userId);
    return BeanCopyUtils.copyBean(user,UserVo.class);
  }
}
