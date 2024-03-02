package com.example.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.client.domain.User;
import com.example.client.service.UserService;
import com.example.core.exception.UserException;
import com.example.domain.LoginVo;
import com.example.service.IAuthStrategy;
import com.example.core.domain.model.user.PasswordLoginBody;
import com.example.core.util.JsonUtils;
import com.example.core.util.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("Password" + IAuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class PasswordAuth implements IAuthStrategy {

    private final UserService userService;

    @Override
    public LoginVo login(String body) {
        PasswordLoginBody loginBody = JsonUtils.parseObject(body, PasswordLoginBody.class);
        ValidatorUtils.validate(loginBody);
        // 密码要用加密satoken自带BCrypt.hashpw(password)  BCrypt.checkpw(password, user.getPassword())
        String password = loginBody.getPassword();
        // TODO 记得加上密码的加密 BCrypt.checkpw
        String account = loginBody.getAccount();
        // 检查用户名是否存在
        boolean usernameExists = userService.exists(new LambdaQueryWrapper<User>().eq(User::getAccount,account));
        if (!usernameExists) {
            throw new UserException("账号不存在: %s",account);
        }
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<User>()
                .eq(User::getPassword,password)
                .eq(User::getAccount,account);
        boolean exists = userService.exists(qw);
        if (exists){
            Long id = userService.getOne(qw).getId();
            StpUtil.login(id);
            String tokenValueByLoginId = StpUtil.getTokenValueByLoginId(id);
            return new LoginVo(tokenValueByLoginId);
        }else throw new UserException("密码错误");
    }

}
