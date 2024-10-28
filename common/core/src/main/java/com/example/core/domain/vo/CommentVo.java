package com.example.core.domain.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: CommentVo
 * @description:推文展示
 * @author: kai
 * @create: 2024-10-26 21:13
 */
@Data
public class CommentVo {
    private Long commentId;
    private Long toCommentId;
    private String toCommentUserName;
    private Long userId;
    private String userName;
    private String avatar;
    private String content;
    private Date CommentTime;
    private List<CommentVo> children;
}
