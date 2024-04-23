package com.example.client.aspect;


import com.example.client.annotation.Notify;
import com.example.client.service.*;
import com.example.core.domain.R;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Aspect
@Component
public class NotificationAspect {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ParticipationsService participationsService;

    @Autowired
    private AccusationService accusationService;

    @AfterReturning(value = "@annotation(notify)", returning = "response")
    public void afterReturningAdvice(JoinPoint joinPoint, Notify notify, R<String> response) {
        if (response.getCode() == 200) {
            Object[] args = joinPoint.getArgs();
            List<Long> idList = extractIdList(args);
            Integer status = extractStatus(args);
            // 使用注解提供的标题和类型
            String title = notify.title();
            String contentTemplate = notify.contentTemplate();
            String type = notify.type();
            // 根据状态发送通知
            sendNotificationsBasedOnStatus(idList, status, title, contentTemplate, type);
        }
    }

    private void sendNotificationsBasedOnStatus(List<Long> idList, Integer status, String title, String contentTemplate, String type) {
        for (Long id : idList) {
            // 根据通知类型和状态构造消息
            String message = constructMessageBasedOnTypeAndStatus(id, status, type);
            // 发送通知
            notificationService.createAndSendNotification(id, title, message);
        }
    }

    private String constructMessageBasedOnTypeAndStatus(Long id, Integer status, String type) {
        return switch (type) {
            case "举报" -> createReportNotificationMessage(id, status);
            case "活动报名" -> createActivitySignUpNotificationMessage(id, status);
            default -> "";
        };
    }

    /**
     * 发送举报信息
     * @param id
     * @param status
     * @return Stirng
     */
    private String createReportNotificationMessage(Long id, Integer status) {
        String username = accusationService.getUserNameById(id); // 假设此方法获取用户名
        // 构造举报相关的消息内容
        String message = "";
        switch (status) {
            // TODO 举报入口好像没有填写活动名称的地方
            case 2 -> {
                message = "您举报的用户"+username+"在活动"+" "+"期间存在违规行为已举报成功，已对该用户进行警告和处罚。祝您生活愉快！";
            }
            case 3 -> {
                message = "您举报的用户"+username+"在活动"+" "+"期间存在违规行为未举报成功。原因为证据不充分。请您提供更加充分有效的证据信息！";
            }
            default -> {
                message = "举报状态未知。";
            }
        };
        return message;
    }

    private String createActivitySignUpNotificationMessage(Long id, Integer status) {
        String eventName = participationsService.getUserNameById(id); // 假设此方法根据参与者ID获取活动名称
        // 构造活动报名相关的消息内容
        return switch (status) {
            case 1 -> "您报名的活动 " + eventName + " 已提交。";
            case 2 -> "恭喜，您已成功报名活动 " + eventName + "！";
            case 3 -> "很遗憾，您报名的活动 " + eventName + " 为通过审核。";
            default -> "报名状态未知。";
        };
    }

    // 从方法参数中提取idList
    private List<Long> extractIdList(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof List) {
                return (List<Long>) arg;
            }
        }
        return Collections.emptyList();
    }

    // 从方法参数中提取status
    private Integer extractStatus(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Integer) {
                return (Integer) arg;
            }
        }
        return null;
    }
}
