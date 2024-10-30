package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分组与联系人关系表(GroupContacts)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupContacts  {

    //分组ID，关联contact_groups表
    private Integer groupId;
    //联系人ID，关联contacts表
    private Integer contactId;



}

