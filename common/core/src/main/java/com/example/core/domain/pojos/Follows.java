package com.example.core.domain.pojos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Follows)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follows  {
    //主键@TableId
    private Long id;

    //用户id
    private Long userId;
    //需求id
    private Long eventId;
    //关注时间
    private Date followTime;



}

