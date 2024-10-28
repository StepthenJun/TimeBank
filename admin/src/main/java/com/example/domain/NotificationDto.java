package com.example.domain;

import com.example.core.domain.pojos.Notification;
import lombok.Data;

/**
 * @program: TimeBank
 * @ClassName: NotificationDto
 * @description:
 * @author: kai
 * @create: 2024-10-26 23:45
 */
@Data
public class NotificationDto extends Notification {
    private Integer targetId;
    //目标类型（1-专业，2-班级，3-年级）@TableId
    private Integer targetType;


}
