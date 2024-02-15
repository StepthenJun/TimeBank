package com.example.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.client.domain.User;
import com.example.client.service.UserService;
import com.example.core.exception.UserException;
import com.example.domain.LoginVo;
import com.example.service.IAuthStrategy;
import com.example.core.domain.model.PasswordLoginBody;
import com.example.core.util.JsonUtils;
import com.example.core.util.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordAuth implements IAuthStrategy {
    @Autowired
    private UserService userService;

    @Override
    public LoginVo login(String body) {
        PasswordLoginBody loginBody = JsonUtils.parseObject(body, PasswordLoginBody.class);
        ValidatorUtils.validate(loginBody);
        String password = loginBody.getPassword();
        String username = loginBody.getUsername();
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_name",username);
        qw.eq("password",password);
        boolean exists = userService.exists(qw);
        if (exists){
            Long id = userService.getOne(qw).getId();
            StpUtil.login(id);
            String tokenValueByLoginId = StpUtil.getTokenValueByLoginId(id);
            return new LoginVo(tokenValueByLoginId);
        }

        // 检查用户名是否存在
        boolean usernameExists = userService.count(new QueryWrapper<User>().eq("user_name", username)) > 0;
        if (!usernameExists) {
            throw new UserException("账号不存在",username);
        }
        throw new UserException("密码错误");
    }

}
