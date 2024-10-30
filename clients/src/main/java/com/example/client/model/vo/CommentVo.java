package com.example.client.model.vo;

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
    private Integer commentId;
    private Integer parentCommentId;
    private String toCommentUserName;
    private Integer userId;
    private String userName;
    private String avatar;
    private String content;
    private Date CommentTime;
    private List<CommentVo> children;
}
