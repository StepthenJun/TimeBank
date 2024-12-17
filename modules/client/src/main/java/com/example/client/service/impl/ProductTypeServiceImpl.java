package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.ProductType;
import com.example.client.service.ProductTypeService;
import com.example.client.mapper.ProductTypeMapper;
import java.util.List;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【product_type】的数据库操作Service实现
* @createDate 2024-11-25 15:59:48
*/
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType>
    implements ProductTypeService{

  @Override
  public List<ProductType> getTypes() {
    return list();
  }
}




