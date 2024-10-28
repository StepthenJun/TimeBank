package com.example.core.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Posts;
@Mapper
/**
 * 帖子信息表(Posts)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:59
 */
public interface PostsMapper extends BaseMapper<Posts> {

}

