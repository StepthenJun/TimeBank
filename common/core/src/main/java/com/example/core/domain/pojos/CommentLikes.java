package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 评论点赞信息表(CommentLikes)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikes  {
    @TableId
    private Integer likeId;

    //评论ID，关联comments表
    private Integer commentId;
    //点赞用户ID，关联user_profile表
    private Integer userId;
    //点赞时间
    private Date likeTime;



}

