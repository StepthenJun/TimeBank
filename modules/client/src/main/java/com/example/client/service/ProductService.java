package com.example.client.service;

import com.example.client.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.client.domain.dto.ProductDto;
import java.util.List;

/**
* @author 86187
* @description 针对表【product】的数据库操作Service
* @createDate 2024-11-20 16:00:24
*/
public interface ProductService extends IService<Product> {
  List<ProductDto> getList();
}
