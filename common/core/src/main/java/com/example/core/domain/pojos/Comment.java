package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (Comment)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment  {
    //留言id@TableId
    private Long id;

    //留言内容
    private String content;
    //发布者id
    private Integer createBy;
    //删除标志
    private String delFlag;
    //留言对象
    private Long eventId;
    //创建时间
    private Date createTime;
    //回复信息
    private String reply;



}

