package com.example.client.controller;

import com.example.core.constant.GlobalConstant;
import com.example.core.domain.R;

import com.example.redis.util.RedisUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;

@Validated
@RestController
@RequestMapping("/address")
public class GetAddress {
    // 获取位置并报错到缓存中
    @GetMapping
    public R<Void> saveaddress(String phone, BigDecimal x,BigDecimal y){
        HashMap<String,Object> map = new HashMap<>();
        try {
            map.put("phone",phone);
            map.put("x",x);
            map.put("y",y);
            RedisUtils.setCacheObject(GlobalConstant.ADDRESS_SGIN,map);
            return R.ok();
        }catch (Exception e){
         return R.fail();
        }
    }
}
