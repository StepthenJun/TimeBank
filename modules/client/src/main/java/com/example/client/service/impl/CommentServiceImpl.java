package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Comment;
import com.example.client.service.CommentService;
import com.example.client.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-02-19 13:30:25
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




