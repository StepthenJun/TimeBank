package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义通知的发送目标，如专业、班级或年级(NotificationTargets)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTargets  {
    //通知ID，外键@TableId
    private Integer notificationId;
    //目标ID@TableId
    private Integer targetId;
    //目标类型（1-专业，2-班级，3-年级）@TableId
    private Integer targetType;




}

