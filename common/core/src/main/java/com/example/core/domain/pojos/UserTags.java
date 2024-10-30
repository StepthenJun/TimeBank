package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 用户与标签的关系表(UserTags)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTags  {
    @TableId
    private Integer userTagId;

    //用户ID，关联user_profile表
    private Integer userId;
    //标签ID，关联tags表
    private Integer tagId;



}

