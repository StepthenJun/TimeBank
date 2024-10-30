package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义不同类型的通知(NotificationTypes)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTypes  {
    //通知类型ID@TableId
    private Integer typeId;

    //通知类型名称
    private String typeName;
    //通知类型描述
    private String description;



}

