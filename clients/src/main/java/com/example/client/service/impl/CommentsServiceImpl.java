package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.mapper.CommentsMapper;
import com.example.client.service.CommentsService;
import com.example.core.domain.pojos.Comments;
import org.springframework.stereotype.Service;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-10-28 19:09:41
 */
@Service("commentsService")
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

}
