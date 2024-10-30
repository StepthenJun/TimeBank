package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 用户与社区的关系表(UserCommunities)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommunities  {
    @TableId
    private Integer userCommunityId;

    //用户ID，关联user_profile表
    private Integer userId;
    //社区ID，关联communities表
    private Integer communityId;


    public UserCommunities(Integer userId, Integer communityId) {
        this.userId = userId;
        this.communityId = communityId;
    }
}

