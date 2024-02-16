package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.api.IdCardAuth;
import com.example.client.domain.AuthIdCard;
import com.example.client.domain.Event;
import com.example.client.domain.User;
import com.example.client.domain.vo.EventVo;
import com.example.client.domain.vo.UserVo;
import com.example.client.service.AuthIdCardService;
import com.example.client.service.EventService;
import com.example.client.service.UserService;
import com.example.core.domain.R;
import com.example.core.domain.model.AuthIdCardBody;
import com.example.core.util.ValidatorUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@SaIgnore
@RequestMapping("/user")
// TODO @SaIgnore要删去
public class UserController {

    @Autowired
    private AuthIdCardService authIdCardService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    /**
     * 用户进行实名认证
     * @return String 提示信息
     */

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


    /**
     * 主页获取用户信息
     * @return UserVo 用户可显信息
     */
    @GetMapping("/index/{id}")
    public R<UserVo> getUserMsg(@NotNull(message = "主键不能为空") @PathVariable Long userId){
        // 感觉有问题，如果区分志愿者和服务者的话返回的主页一样吗？
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        UserVo userVo = BeanUtil.copyProperties(one, UserVo.class);
        return R.ok(userVo);
    }

    /**
     * 用户查看自己发布的需求
     * @return eventvos 发布的需求
     */
    @GetMapping("/publishevents")
    public R<List<EventVo>> getpublisheventsByuser(@RequestParam Long userId){
        List<Event> events = eventService.list(new LambdaQueryWrapper<Event>()
                .eq(Event::getId, userId));
        List<EventVo> eventVos = events.stream()
                .map(event -> {
                    EventVo eventVo = new EventVo();
                    BeanUtil.copyProperties(event, eventVo);
                    return eventVo;
                })
                .toList();
        return R.ok(eventVos);
    }

}
