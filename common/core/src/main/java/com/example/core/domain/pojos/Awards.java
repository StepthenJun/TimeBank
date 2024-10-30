package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 用户获奖信息表(Awards)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Awards  {
    @TableId
    private Integer awardId;

    //用户ID，关联user_profile表
    private Integer userId;
    //奖项名称
    private String awardName;
    //奖项等级
    private String awardLevel;



}

