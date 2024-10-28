package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (Participations)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participations  {
    //主键@TableId
    private Long id;

    //需求id
    private Long eventId;
    //志愿者id
    private Long participationId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //状态：1已申请、2已接受、3已完成
    private Integer status;



}

