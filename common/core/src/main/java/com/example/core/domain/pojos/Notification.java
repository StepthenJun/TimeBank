package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (Notification)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification  {
    //主键@TableId
    private Long id;

    //用户id
    private Long userId;
    //标题
    private String title;
    //通知内容
    private String content;
    //发送时间
    private Date sendTime;
    //是否已读
    private Integer isRead;



}

