package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.example.client.domain.Notification;
import com.example.client.service.NotificationService;
import com.example.core.domain.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@SaIgnore
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    // 接收通知历史请求
    @GetMapping("/user/{userId}")
    public R<List<Notification>> getNotifications(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return R.ok(notifications);
    }
}
