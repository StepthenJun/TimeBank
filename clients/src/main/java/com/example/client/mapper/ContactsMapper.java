package com.example.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.domain.pojos.Contacts;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 用户联系人关系表(Contacts)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-26 16:02:57
 */
public interface ContactsMapper extends BaseMapper<Contacts> {

}

