package com.example.admin.service.impl;

import com.example.admin.domain.LoginVo;
import com.example.admin.service.IAuthStrategy;
import org.core.constant.Captcha;
import org.redis.util.RedisUtils;

public class SmsAuth implements IAuthStrategy {


    @Override
    public LoginVo login(String body) {

        return null;
    }

    /**
     * 校验短信验证码
     */
    private boolean validateSmsCode(String tenantId, String phonenumber, String smsCode) {
        // 存入缓存也用这个Captcha.CAPTCHA_CODE_KEY + phonenumber作为key
        String code = RedisUtils.getCacheObject(Captcha.CAPTCHA_CODE_KEY + phonenumber);
        return code.equals(smsCode);
    }
}
