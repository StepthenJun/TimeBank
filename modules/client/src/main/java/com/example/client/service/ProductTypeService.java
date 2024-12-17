package com.example.client.service;

import com.example.client.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
* @author 86187
* @description 针对表【product_type】的数据库操作Service
* @createDate 2024-11-25 15:59:48
*/
public interface ProductTypeService extends IService<ProductType> {

  List<ProductType> getTypes();

}
