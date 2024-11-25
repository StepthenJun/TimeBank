package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Product;
import com.example.client.domain.User;
import com.example.client.domain.dto.ProductDto;
import com.example.client.service.ProductService;
import com.example.client.mapper.ProductMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【product】的数据库操作Service实现
* @createDate 2024-11-20 16:00:24
*/
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

  private final ProductMapper productMapper;
  @Override
  public List<ProductDto> getList() {
    List<ProductDto> list = productMapper.selectJoinList(ProductDto.class,new MPJLambdaWrapper<Product>()
        .selectAll(Product.class)
        .leftJoin(
            User.class,
            User::getId,
            Product::getCreateUserId,
            ext -> ext.selectAs(User::getUserName, ProductDto::getCreateBy)
        ));
    return list;
  }
}




