package com.example.admin;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.api.IdCardAuth;
import org.core.constant.Captcha;
import org.example.util.AliyunSmsUtil;
import org.redis.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.HashMap;

@SpringBootTest
class AdminApplicationTests {
    @Autowired
    private AliyunSmsUtil aliyunSmsUtil;

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
    void smsTest(){
        String phonenumber = "18750851691";
        String key = Captcha.CAPTCHA_CODE_KEY + phonenumber;
        String code = RandomUtil.randomNumbers(4);

        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(Captcha.CAPTCHA_EXPIRATION));
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        boolean b = aliyunSmsUtil.sendMsg(phonenumber, map);
        if (b){
            System.out.println(code);
        }
        else System.out.println("失败");
    }
    @Test
    void login(){
        StpUtil.login(1,new SaLoginModel().setExtra("name","张山"));
        System.out.println(StpUtil.getExtra("name"));
        System.out.println(StpUtil.getTokenValueByLoginId(1));
    }


}
