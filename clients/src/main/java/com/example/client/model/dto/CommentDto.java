package com.example.client.model.dto;

import com.example.core.domain.pojos.Comments;
import lombok.Data;

/**
 * @program: TimeBank
 * @ClassName: CommentDto
 * @description:
 * @author: kai
 * @create: 2024-10-28 21:30
 */
@Data

public class CommentDto extends Comments {
    private Integer parentCommentId;
}
