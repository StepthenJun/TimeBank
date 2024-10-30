package com.example.core.domain.pojos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 帖子信息表(Posts)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts  {
    @TableId
    private Integer postId;

    //用户ID，关联user_profile表
    private Integer userId;
    //社区ID，关联communities表
    private Integer communityId;
    //帖子发布时间
    private Date postTime;
    //帖子定时发布时间
    private Date scheduledTime;
    //媒体文件URL
    private String imageUrl;
    //帖子内容
    private String comments;
    //点赞数
    private Integer likes;
    //帖子可见性（1-仅自己可见，2-对好友可见，3-所有人可见）
    private Integer visibility;



}

