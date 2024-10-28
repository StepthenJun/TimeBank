package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 存储通知详细信息，包括定时发送功能(Notifications)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notifications  {
    //通知ID@TableId
    private Integer notificationId;

    //通知类型ID，外键
    private Integer typeId;
    //通知标题
    private String title;
    //通知内容
    private String content;
    //预定发送时间
    private Date scheduledTime;
    //通知状态
    private Object status;
    //通知创建时间
    private Date creationTime;



}

