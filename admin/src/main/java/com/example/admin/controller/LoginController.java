package com.example.admin.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.core.constant.Captcha;
import org.core.domain.R;
import org.core.util.ValidatorUtils;
import org.example.util.AliyunSmsUtil;
import org.redis.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate redisTemplate;
    private final AliyunSmsUtil aliyunSmsUtil;
    /**
     * 发送验证码：
     * 在redis中用hash存储用户的相关信息，用PHONE_NUM+手机号作为用户hash的key，
     * “code”作为用户信息hash中验证码的小key，查询redis中用户的验证码信息，
     * "num"是验证次数的小key
     */
/*    @PostMapping("/sendCaptcha")
    public String sendCaptcha(String phone){
        //验证码verCode
        String verCode;
        String key = "PHONE_NUM"+phone;
        //如果redis中有缓存的验证码
        Object object = redisTemplate.opsForHash().get(key, "code");
        if(null != object){
            return "该用户验证码已发送，且未过期，请输入验证码登录或注册！";
        }else {
            Random r = new Random(System.currentTimeMillis());
            int low = 100000;
            int high = 999999;
            //根据时间随机生成验证码verCode，将其放入redis中
            int code = (r.nextInt(high - low) + low);
            verCode = String.valueOf(code);
            redisTemplate.opsForHash().put(key,"code",verCode);
            //放入检验次数num=5
            redisTemplate.opsForHash().put(key,"num",5);
            //设置过期时间
            redisTemplate.expire(key,60*5, TimeUnit.SECONDS);
        }
        try {
            //调用发送验证码的接口发送验证码
            //把验证码打包成hashmap传参
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("code", verCode);
            aliyunSmsUtil.sendMsg(phone, hashMap);
        }catch (Throwable throwable){
            redisTemplate.delete(key);
            return "短信发送失败！";
        }
        return "发送成功";
    }*/

    @GetMapping("/sms/code")
    public R<Void> smsCode(@NotBlank(message = "{user.phonenumber.not.blank}") String phonenumber) {
        String key = Captcha.CAPTCHA_CODE_KEY + phonenumber;
        String code = RandomUtil.randomNumbers(4);
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(Captcha.CAPTCHA_EXPIRATION));
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        boolean b = aliyunSmsUtil.sendMsg(phonenumber, map);
        if (!b) {
            log.error("验证码短信发送异常");
            return R.fail();
        }
        return R.ok();
    }

    // 登录界面
    @PostMapping("/login")
    public Boolean login() {
        // 校验传参
        ValidatorUtils.validate(1);
        return null;
    }

    // 注册
    @PostMapping("/register")
    public R<Void> register(@Validated @RequestBody String user) {
        return R.ok();
    }
    // 找回密码
    @PostMapping("/findbackpw")
    public R<String> findBackPw(){
        return null;
    }
}
