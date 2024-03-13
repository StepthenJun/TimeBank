package com.example.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Notification;
import com.example.client.service.NotificationService;
import com.example.client.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author 86187
* @description 针对表【notification】的数据库操作Service实现
* @createDate 2024-03-10 10:44:20
*/
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{

    private final SimpMessagingTemplate messagingTemplate;

    public void createAndSendNotification(Long userId, String title, String content) {
        // 创建通知对象
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setSendTime(LocalDateTime.now());
        notification.setIsRead(false);

        // 保存通知到数据库
        save(notification);

        // 发送实时通知
        messagingTemplate.convertAndSendToUser(
                userId.toString(), "/queue/notifications", notification);
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        // 从数据库中检索用户的历史通知
        return list(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getUserId, userId)
                .orderByDesc(Notification::getSendTime));
    }
}




