package com.example.core.domain.pojos;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (Accusation)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accusation  {
    //举报id@TableId
    private Long id;

    //举报信息
    private String content;
    //创建者id
    private Long createBy;
    //举报对象id（需求或者是志愿者）
    private Long createTo;
    //状态（未审核0，已审核1）
    private String status;
    //原因
    private String reason;
    //图片地址
    private String imageUrl;



}

