//package com.example.service.impl;
//
//import cn.dev33.satoken.stp.StpUtil;
//import cn.hutool.core.util.ObjectUtil;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//
//import com.example.core.domain.model.user.SmsLoginBody;
//import com.example.core.domain.pojos.User;
//import com.example.core.enums.UserStatus;
//import com.example.core.exception.UserException;
//import com.example.core.service.UserService;
//import com.example.core.util.JsonUtils;
//import com.example.core.util.ValidatorUtils;
//import com.example.domain.LoginVo;
//import com.example.service.IAuthStrategy;
//import com.example.core.constant.Captcha;
//import com.example.redis.util.RedisUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service("Sms" + IAuthStrategy.BASE_NAME)
//@RequiredArgsConstructor
//public class SmsAuth implements IAuthStrategy {
//
//    private final UserService userService;
//
//
//    @Override
//    public LoginVo login(String body) {
//        SmsLoginBody loginBody = JsonUtils.parseObject(body, SmsLoginBody.class);
//        ValidatorUtils.validate(loginBody);
//        log.info(":{}",loginBody);
//        String code = loginBody.getCode();
//        String phone = loginBody.getPhone();
//        User user = loadUserByPhonenumber(phone);
//        boolean b = validateSmsCode(phone, code);
//        if (b){
//            Long id = user.getId();
//            StpUtil.login(id);
//            String tokenValueByLoginId = StpUtil.getTokenValueByLoginId(id);
//            return new LoginVo(tokenValueByLoginId);
//        }
//        return LoginVo.withError("错误","验证码错误");
//    }
//
//    /**
//     * 校验短信验证码
//     */
//    private boolean validateSmsCode(String phonenumber, String smsCode) {
//        // 存入缓存也用这个Captcha.CAPTCHA_CODE_KEY + phonenumber作为key
//        // TODO 如果随机填写验证码还没发送 会抛出异常 这里需要捕获一下
//        String code = RedisUtils.getCacheObject(Captcha.CAPTCHA_CODE_KEY + phonenumber);
//        if (code == null){
//            throw new RuntimeException("请先发送短信验证吗");
//        }
//        return code.equals(smsCode);
//    }
//
//    private User loadUserByPhonenumber(String phonenumber) {
//        User one = userService.getOne(new LambdaQueryWrapper<User>()
//                .eq(User::getPhonenumber, phonenumber));
//        if (ObjectUtil.isEmpty(one)){
//            throw new UserException("账号不存在{},请先注册", phonenumber);
//        }else if (UserStatus.DISABLE.getCode().equals(one.getStatus())){
//            throw new UserException("账号已被停用{}",phonenumber);
//        }
//        return one;
//    }
//}
