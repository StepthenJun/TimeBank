package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 用户联系人分组信息表(ContactGroups)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactGroups  {
    @TableId
    private Integer groupId;

    //用户ID，关联user_profile表
    private Integer userId;
    //分组名称
    private String groupName;
    //分组描述
    private String description;



}

