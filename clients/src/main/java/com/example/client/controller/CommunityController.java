package com.example.client.controller;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.client.model.vo.RankList;
import com.example.client.model.vo.UserDetailVo;
import com.example.client.service.CommunitiesService;
import com.example.client.service.UserCommunitiesService;
import com.example.client.service.UserService;
import com.example.core.domain.R;
import com.example.core.domain.pojos.Communities;
import com.example.core.domain.pojos.UserCommunities;
import com.example.core.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: TimeBank
 * @ClassName: CommunityController
 * @description:
 * @author: kai
 * @create: 2024-10-25 20:07
 */
@RestController
@RequestMapping("/com/example/core/community")
public class CommunityController{
    @Autowired
    private CommunitiesService communitiesService;
    @Autowired
    private UserCommunitiesService umService;
    @Autowired
    private CommunitiesService communitiesService;
    @Autowired
    private UserService userService;
    @GetMapping("/show")
    public R<List<Communities>> showCommunities(@RequestParam Integer tagId){
        LambdaQueryWrapper<Communities> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Communities::getTagId,tagId);
        List<Communities> communities = communitiesService.list(queryWrapper);
        return R.ok(communities);
    }
    @PostMapping("/follow")
    public R followCommunity(@RequestParam Integer communityId){
        if(umService.getById(communityId)!=null)
            return R.fail("已经关注");
        umService.save(new UserCommunities((Integer) StpUtil.getLoginId(),communityId));
        return R.ok();
    }
    @GetMapping("/search")
    public R<List<Communities>> searchCommunity(String content){
        LambdaQueryWrapper<Communities> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(content!=null&&content.length()!=0,Communities::getCommunityName,content);
        List<Communities> communities = communitiesService.list(queryWrapper);
        return R.ok(communities);
    }
    @GetMapping("/getInfo")
    public R<Communities> getCommunityInfo(@RequestParam Integer id){
        communitiesService.getById(id);
        return R.ok(new Communities());
    }
    @GetMapping("/rank")
    public R<List<RankList>> getRankList(@RequestParam Integer tagId){
        LambdaQueryWrapper<Communities> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Communities::getTagId,tagId).last("limit 3");
        List<Communities> communities = communitiesService.list(queryWrapper);
        List<RankList> rankLists = BeanCopyUtils.copyBeanList(communities, RankList.class);
        for (RankList rankList : rankLists) {
            List<UserCommunities> list = umService.list(new LambdaQueryWrapper<UserCommunities>().
                    eq(UserCommunities::getCommunityId, rankList.getCommunityId()));
            rankList.setUserRankList(toUserRank(list));
        }
        return R.ok(rankLists);
    }

    private List<UserDetailVo> toUserRank(List<UserCommunities> list) {
        List<Integer> userIds = list.stream().map(userCommunities -> userCommunities.getUserId()).collect(Collectors.toList());
        return new ArrayList<>();
    }

    @GetMapping("/otherRank")
    public R<List<UserDetailVo>> getUserRankList(@RequestParam Long tagId){
        return R.ok(new ArrayList<>());
    }

}
