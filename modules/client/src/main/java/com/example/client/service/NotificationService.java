package com.example.client.service;

import com.example.client.domain.Notification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86187
* @description 针对表【notification】的数据库操作Service
* @createDate 2024-03-10 10:44:20
*/
public interface NotificationService extends IService<Notification> {

    void createAndSendNotification(Long userId, String title, String content);

    List<Notification> getNotificationsByUserId(Long userId);
}
