package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.example.client.util.GeoUtil;
import com.example.core.domain.R;

import com.example.core.domain.model.AddressBody;
import com.example.core.util.ValidatorUtils;
import com.example.redis.util.RedisUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@SaIgnore
@RequestMapping("/address")
public class AddressController {
    // 签到进行计时，把key和member记到缓存中
    @GetMapping("/signIn")
    public R<String> saveaddress(@Validated @RequestBody AddressBody addressBody){
        ValidatorUtils.validate(addressBody);
        Double eventLon = addressBody.getEventLon();
        Double eventLat = addressBody.getEventLat();
        Double userLat = addressBody.getUserLat();
        Double userLon = addressBody.getUserLon();
        boolean withinRadius = GeoUtil.isWithinRadius(eventLat, eventLon, userLat, userLon, 1000);
        if (withinRadius){
            RedisUtils.recordSignIn((Long) StpUtil.getLoginId(),addressBody.getEventId());
            return R.ok("计时开始");
        }else {
            return R.fail("请到指定位置开始服务");
        }
    }

    @PostMapping("/signOut")
    public R<String> signOut(@Validated @RequestBody AddressBody addressBody){
        String signInTime = RedisUtils.getSignInTime((Long) StpUtil.getLoginId(), addressBody.getEventId());

        return R.ok("计时结束");
    }
}
