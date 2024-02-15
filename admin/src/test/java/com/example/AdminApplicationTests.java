package com.example;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.client.domain.User;
import com.example.client.service.UserService;
import com.example.api.IdCardAuth;
import com.example.core.constant.Captcha;
import com.example.redis.util.RedisUtils;
import org.example.sms.util.AliyunSmsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;
import java.util.HashMap;

@SpringBootTest(classes = AdminApplication.class)
@ComponentScan()
class AdminApplicationTests {
    @Autowired
    private  UserService userService;
    @Test
    void idcardauth() {
        String cardno = "350427200408161034";
        String realname = "严文骏";
        String responseString = IdCardAuth.AuthIdCard(cardno, realname);
        // 使用Fastjson解析JSON响应
        JSONObject jsonObj = JSON.parseObject(responseString);

        // 提取 'isok' 和 'birthday' 字段
        boolean isOk = jsonObj.getJSONObject("result").getBoolean("isok");
        String birthday = jsonObj.getJSONObject("result").getJSONObject("IdCardInfor").getString("birthday");

        // 可以根据需要返回这些值，打印它们或进行进一步处理
        System.out.println("Is OK: " + isOk);
        System.out.println("Birthday: " + birthday);
        System.out.println(responseString);
    }

    @Test
    void smsTest() throws Exception {
        String phonenumber = "18750851691";
        String key = Captcha.CAPTCHA_CODE_KEY + phonenumber;
        String code = RandomUtil.randomNumbers(4);

        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(Captcha.CAPTCHA_EXPIRATION));
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        boolean b = AliyunSmsUtil.sendMsg(phonenumber, map);
        if (b){
            System.out.println(code);
        }
        else System.out.println("失败");
    }

    @Test
    void sendSms() throws Exception {
        String phonenumber = "18750851691";
        String code = RandomUtil.randomNumbers(4);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);
        AliyunSmsUtil.sendMsg(phonenumber,map);
    }
    @Test
    void login(){
        String username = "test";
        String password = "123456";
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_name",username);
        qw.eq("password",password);
        boolean exists = userService.exists(qw);
        if (exists){
            Long id = userService.getOne(qw).getId();
            StpUtil.login(id);
            String tokenValueByLoginId = StpUtil.getTokenValueByLoginId(id);
            System.out.println(tokenValueByLoginId);
        }

        // 检查用户名是否存在
        boolean usernameExists = userService.count(new QueryWrapper<User>().eq("user_name", username)) > 0;
        if (!usernameExists) {
            System.out.println("账户错误用户名不存在");
        }
        System.out.println("密码错误提供的密码不正确");
    }
    }
