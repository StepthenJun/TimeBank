package com.example.client.mapper;

import com.example.client.domain.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86187
* @description 针对表【product_type】的数据库操作Mapper
* @createDate 2024-11-25 15:59:48
* @Entity com.example.client.domain.ProductType
*/
@Mapper
public interface ProductTypeMapper extends MPJBaseMapper<ProductType> {

}




