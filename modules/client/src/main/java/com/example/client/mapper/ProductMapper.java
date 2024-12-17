package com.example.client.mapper;

import com.example.client.domain.Product;

import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86187
* @description 针对表【product】的数据库操作Mapper
* @createDate 2024-11-20 16:00:24
* @Entity com.example.client.domain.Product
*/
@Mapper
public interface ProductMapper extends MPJBaseMapper<Product> {

}




