package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.bean.BeanUtil;
import com.example.api.IdCardAuth;
import com.example.client.domain.AuthIdCard;
import com.example.client.service.AuthIdCardService;
import com.example.core.domain.R;
import com.example.core.domain.model.AuthIdCardBody;
import com.example.core.util.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthIdCardService authIdCardService;


    @SaIgnore
    @PostMapping("/authIdCard")
    public R<String> authIdCard(@Validated @RequestBody AuthIdCardBody authBody){
        ValidatorUtils.validate(authBody);
        String realName = authBody.getRealName();
        String idNumber = authBody.getIdNumber();
        String responseString = IdCardAuth.AuthIdCard(idNumber, realName);
        // 使用Fastjson解析JSON响应
        JSONObject jsonObj = JSON.parseObject(responseString);

        // 提取 'isok' 和 'birthday' 字段
        boolean isOk = jsonObj.getJSONObject("result").getBoolean("isok");
        AuthIdCard authIdCard = BeanUtil.copyProperties(authBody, AuthIdCard.class);
        log.info("json:{}",jsonObj);
        return isOk ? R.ok("实名认证成功") : R.fail("实名认证失败");
    }
}
