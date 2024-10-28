package client.controller;

import client.model.vo.CommentVo;
import com.example.core.domain.R;
import com.example.core.domain.pojos.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: CommentController
 * @description:
 * @author: kai
 * @create: 2024-10-25 20:06
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @GetMapping("/show")
    public R<List<CommentVo>> showComment(@RequestParam Long postId){
        return R.ok(new ArrayList<CommentVo>());
    }
    @PostMapping("/write")
    public R writeComment(@RequestParam Long parentCommentId){
        return R.ok();
    }
    @PostMapping("thumb")
    public R thumbComment(@RequestParam Long CommentId){
             return R.ok();
    }

}
