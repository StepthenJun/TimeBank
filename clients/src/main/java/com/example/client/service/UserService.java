package com.example.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.client.model.vo.UserVo;
import com.example.core.domain.pojos.User;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2024-10-26 15:58:26
 */
public interface UserService extends IService<User> {

  UserVo getUserInfo(long userId);
}
