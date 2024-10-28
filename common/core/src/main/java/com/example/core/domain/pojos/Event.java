package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (Event)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event  {
    //需求id@TableId
    private Long id;

    //需求名称
    private String name;
    //需求描述
    private String description;
    //需求人数
    private Integer needNum;
    //创建时间
    private Date createTime;
    //执行时间
    private Date executionTime;
    //地址信息
    private String address;
    //发布状态（未审核0，不可用2，可用3，已完成）
    private String status;
    //未删除0，已删除1
    private String delFlag;
    //奖励时间币的个数
    private Integer reward;
    //创立者id
    private Long createBy;
    //当前报名人数
    private Integer curentNum;
    //审核失败原因
    private String defaultReason;
    //图片url位置
    private String imageUrl;
    //发布时间
    private Date publishTime;



}

