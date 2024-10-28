package client.controller;

import client.model.vo.PostVo;
import com.example.core.domain.R;
import com.example.core.domain.pojos.Posts;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kai
 * @date 2024/10/26
 * @description TODO
 */
@RestController
@RequestMapping("/post")
public class PostController {
    @GetMapping("show")
    public R<List<PostVo>> showPosts(@RequestParam Long communityId){
        return R.ok(new ArrayList<PostVo>());
    }
    @PostMapping("/write")
    public R writePost(@RequestBody Posts post){
        return R.ok();
    }
    @GetMapping("/getDetail")
    public  R<Posts> getDetail(@RequestParam Long postId){
        return R.ok(new Posts());
    }


}
