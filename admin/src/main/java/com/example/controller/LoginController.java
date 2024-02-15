package com.example.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.client.service.UserService;
import com.example.core.domain.model.RegisterBody;
import com.example.service.IAuthStrategy;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.core.constant.Captcha;
import com.example.core.domain.R;
import com.example.core.domain.model.LoginBody;
import com.example.core.util.JsonUtils;
import com.example.core.util.ValidatorUtils;
import com.example.redis.util.RedisUtils;
import org.example.sms.util.AliyunSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;

@Slf4j
@SaIgnore
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UserService userService;

    // 发送短信验证码的接口
    @GetMapping("/sms/code")
    public R<Void> smsCode(@NotBlank(message = "手机号不能为空") String phonenumber) throws Exception {
        ValidatorUtils.validate(phonenumber);
        String key = Captcha.CAPTCHA_CODE_KEY + phonenumber;
        String code = RandomUtil.randomNumbers(4);
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(Captcha.CAPTCHA_EXPIRATION));
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        boolean b = AliyunSmsUtil.sendMsg(phonenumber, map);
        if (!b) {
            log.error("验证码短信发送异常");
            return R.fail();
        }
        log.info("验证码为：{}",code);
        return R.ok();
    }

    // 登录界面
    @PostMapping("/login")
    public Boolean login(@Validated @RequestBody String body) throws Exception {
        LoginBody loginBody = JsonUtils.parseObject(body, LoginBody.class);
        // 校验传参
        ValidatorUtils.validate(loginBody);
        String grantType = loginBody.getGrantType();
        IAuthStrategy.login(body,grantType);
        return null;
    }
    // 注册
    @PostMapping("/register")
    public R<Void> register(@Validated @RequestBody RegisterBody registerBody) {
        ValidatorUtils.validate(registerBody);
        String phone = registerBody.getPhone();
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();
        String userType = registerBody.getUserType();
        userService.register(username,password,phone);
        return R.ok();
    }
    // 找回密码
    @PostMapping("/findbackpw")
    public R<String> findBackPw(){
        return null;
    }

    // 退出登录
    @PostMapping("/logout")
    public R<Void> logout() {
        StpUtil.logout();
        return R.ok("退出成功");
    }
}
