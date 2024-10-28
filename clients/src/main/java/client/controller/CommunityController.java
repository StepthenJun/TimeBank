package client.controller;

import client.model.vo.RankList;
import client.model.vo.UserDetailVo;
import com.example.core.domain.R;
import com.example.core.domain.pojos.Communities;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: CommunityController
 * @description:
 * @author: kai
 * @create: 2024-10-25 20:07
 */
@RestController
@RequestMapping("/client/community")
public class CommunityController{
    @GetMapping("/show")
    public R<List<Communities>> showCommunities(@RequestParam Long tagId){
        return R.ok(new ArrayList<Communities>());
    }
    @PostMapping("/follow")
    public R followCommunity(@RequestParam Long communityId){
        return R.ok();
    }
    @GetMapping("/search")
    public R<List<Communities>> searchCommunity(String content){
        return R.ok(new ArrayList<Communities>());
    }
    @GetMapping("/getInfo")
    public R<Communities> getCommunityInfo(@RequestParam Long id){
        return R.ok(new Communities());
    }
    @GetMapping("/rank")
    public R<List<RankList>> getRankList(@RequestParam Long tagId){
        return R.ok(new ArrayList<RankList>());
    }
    @GetMapping("/otherRank")
    public R<List<UserDetailVo>> getUserRankList(@RequestParam Long tagId){
        return R.ok(new ArrayList<>());
    }

}
