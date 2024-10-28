package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 帖子评论表(Comments)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments  {
    @TableId
    private Integer commentId;

    //帖子ID，关联posts表
    private Integer postId;
    //父评论ID，用于多级评论
    private Integer parentCommentId;
    //评论者ID，关联user_profile表
    private Integer userId;
    //评论时间
    private Date commentTime;
    //评论内容
    private String content;
    //评论点赞数
    private Integer likes;



}

