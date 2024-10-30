package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.CommentLikes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 评论点赞信息表(CommentLikes)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:59
 */
public interface CommentLikesMapper extends BaseMapper<CommentLikes> {

}

