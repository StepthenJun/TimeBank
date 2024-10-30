package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.ContactGroups;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 用户联系人分组信息表(ContactGroups)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:59
 */
public interface ContactGroupsMapper extends BaseMapper<ContactGroups> {

}

