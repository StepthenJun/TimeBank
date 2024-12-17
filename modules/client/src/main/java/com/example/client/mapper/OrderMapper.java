package com.example.client.mapper;

import com.example.client.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86187
* @description 针对表【order】的数据库操作Mapper
* @createDate 2024-11-27 21:54:07
* @Entity com.example.client.domain.Order
*/
@Mapper
public interface OrderMapper extends MPJBaseMapper<Order> {

}




