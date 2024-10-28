package com.example.core.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Comments;
@Mapper
/**
 * 帖子评论表(Comments)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface CommentsMapper extends BaseMapper<Comments> {

}

