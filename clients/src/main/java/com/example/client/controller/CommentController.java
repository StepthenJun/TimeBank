package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.example.client.model.dto.CommentDto;
import com.example.client.model.vo.CommentVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.client.service.CommentLikesService;
import com.example.client.service.CommentsService;
import com.example.client.service.UserService;
import com.example.core.domain.R;
import com.example.core.domain.pojos.CommentLikes;
import com.example.core.domain.pojos.Comments;
import com.example.core.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
/**
 * @program: TimeBank
 * @ClassName: CommentController
 * @description:
 * @author: kai
 * @create: 2024-10-25 20:06
 */
@RestController
@SaIgnore
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CommentLikesService commentLikesService;
    @Autowired
    private UserService userService;
    @GetMapping("/show")
    public R<List<CommentVo>> showComment(@RequestParam Long postId){
//        @RequestParam Long postId

        return R.ok(listComments(postId));
    }
    @PostMapping("/write")
    public R writeComment(@RequestBody CommentDto commentDto){
        Comments comments=BeanCopyUtils.copyBean(commentDto,Comments.class);
        comments.setParentCommentId(commentDto.getParentCommentId());
        commentsService.save(comments);
        return R.ok();
    }
    @PostMapping("thumb")
    public R thumbComment(@RequestParam Integer commentId){
        Comments comments = commentsService.getById(commentId);
        comments.addLike();
        commentsService.updateById(comments);
        commentLikesService.save(new CommentLikes(commentId, (Integer) StpUtil.getLoginId(),new Date()));
        return R.ok();
    }

    public List<CommentVo> listComments(Long postId) {
        LambdaQueryWrapper<Comments> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comments::getPostId,postId).eq(Comments::getParentCommentId,-1);
        List<CommentVo> commentVoList = toCommentVoList(commentsService.list(queryWrapper));
        for (CommentVo commentVo : commentVoList) {
            List<CommentVo> children = getChildren(commentVo.getCommentId());
            commentVo.setChildren(children);
        }
        return commentVoList;
    }
    private List<CommentVo> getChildren(Integer id) {
        LambdaQueryWrapper<Comments> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comments::getParentCommentId,id);
        List<Comments> comments = commentsService.list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(comments);
        for (CommentVo commentVo : commentVos) {
            List<CommentVo> children = getChildren(commentVo.getCommentId());
            commentVo.setChildren(children);
        }
        return commentVos;
    }
    private List<CommentVo> toCommentVoList(List<Comments> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历vo集合
        for (CommentVo commentVo : commentVos) {
            if(commentVo.getUserId()!=null){
            String userName = userService.getById(commentVo.getUserId()).getUserName();
            commentVo.setUserName(userName);}
            if(commentVo.getParentCommentId()!=null&&commentVo.getParentCommentId()!=-1){
                System.out.println(commentsService.getById(commentVo.getParentCommentId()).getUserId());
                String toCommentUserName = userService.getById(commentsService.getById(commentVo.getParentCommentId()).getUserId()).getUserName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}
