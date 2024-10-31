package com.example.client.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.example.client.domain.ChatHistory;
import com.example.client.model.dto.ChatDto;
import com.example.client.model.vo.UserDetailVo;
import com.example.client.model.vo.UserVo;
import com.example.client.service.ChatHistoryService;
import com.example.client.service.FollowsService;
import com.example.client.service.UserService;
import com.example.core.domain.R;
import com.example.core.domain.pojos.Follows;
import com.example.core.domain.pojos.Notification;
import com.example.core.domain.pojos.NotificationTypes;
import com.example.core.domain.pojos.User;
import com.example.core.util.BeanCopyUtils;
import com.example.web.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: UserController
 * @description:
 * @author: kai
 * @create: 2024-10-25 20:09
 */
@RestController
@RequestMapping("/com/example/core/user")
public class UserController {
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private ChatHistoryService chatHistoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private FollowsService followsService;

    @GetMapping("/myInfo")
    public R<UserVo> getMyInfo(){
        long loginId = StpUtil.getLoginIdAsLong();
        UserVo userInfo = userService.getUserInfo(loginId);
        return R.ok(userInfo);
    }
    @PutMapping("/updateInfo")
    public R updateInfo(@RequestBody UserVo userVo){
        boolean b = userService.updateById(BeanCopyUtils.copyBean(userVo, User.class));
        if (b){
        return R.ok();
        }
        return R.fail();
    }

    @GetMapping("/getUserInfo")
    public R<UserVo> getUserInfo(@RequestParam Long userId){
        UserVo userInfo = userService.getUserInfo(userId);
        return R.ok(userInfo);
    }


    @PostMapping("/follow")
    public R followUser(@RequestParam Long userId){

        return R.ok();
    }
    @PostMapping("/isAccept")
    public R acceptOrRejectUser(@RequestParam Integer isAccept){
        return R.ok();
    }

    @GetMapping("/showFriends")
    public R<List<UserDetailVo>> showFriends(){
        return R.ok(new ArrayList<UserDetailVo>());
    }
    @GetMapping("/getNotificationTypes")
    public R<List<NotificationTypes>> getNotiTypes(){
        return  R.ok(new ArrayList<>());
    }
    @GetMapping("/getNotifications")
    public R<List<Notification>> getNotification(@RequestParam Long typeId){
        return R.ok(new ArrayList<>());
    }



    // 私聊
    @PostMapping("sendMessage")
    public R sendPrivateMessage(@RequestBody ChatDto chatDto) {
        try {
            Long sendId = chatDto.getSenderId();
            Long toId = chatDto.getReceiverId();
            String message = chatDto.getMessage();
            webSocketServer.sendMessage(String.valueOf(sendId), String.valueOf(toId), message);
            chatHistoryService.save(BeanUtil.copyProperties(chatDto, ChatHistory.class));
        } catch (Exception e) {
            return R.fail("发送消息失败");
        }
        return R.ok();
    }

    // 获取私聊消息
    @GetMapping("/getMessage")
    public R getPrivateMessage(@RequestParam Long senderId, @RequestParam Long receiverId) {
        List<ChatHistory> chatHistories = chatHistoryService.getMessageList(senderId, receiverId);
        return R.ok(chatHistories);
    }
}
