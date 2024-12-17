package com.example.client.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Product;
import com.example.client.domain.ProductType;
import com.example.client.domain.User;
import com.example.client.domain.dto.ProductDto;
import com.example.client.domain.dto.ProductQueryDto;
import com.example.client.domain.vo.ProductVo;
import com.example.client.service.ProductService;
import com.example.client.mapper.ProductMapper;
import com.example.core.util.BeanCopyUtils;
import com.example.core.util.StringUtils;
import com.example.oss.util.FileService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  private final FileService fileService;

  @Override
  public List<ProductVo> getList() {
    List<ProductVo> list = productMapper.selectJoinList(ProductVo.class,new MPJLambdaWrapper<Product>()
        .selectAll(Product.class)
        .leftJoin(
            User.class,
            User::getId,
            Product::getCreateUserId,
            ext -> ext.selectAs(User::getUserName, ProductDto::getCreateBy)
        ));
    return list;
  }


  @Override
  public Boolean publish(ProductDto productDto) {
    List<String> images = fileService.uploadMultipleFiles(productDto.getImages());
    System.out.println(images);
    Product product = BeanCopyUtils.copyBean(productDto, Product.class);
    product.setImage(String.join(",",images));
    product.setCreateUserId(StpUtil.getLoginIdAsInt());
    productMapper.insert(product);
    return true;
  }

  @Override
  public List<ProductDto> getListByType(Integer typeId) {
    MPJLambdaWrapper<Product> lambdaWrapper = new MPJLambdaWrapper<>();
    lambdaWrapper.leftJoin(
        ProductType.class,
        ProductType::getId,
        Product::getTypeId,
        ext -> ext.selectAs(ProductType::getType,ProductDto::getType)
    );
    return productMapper.selectJoinList(ProductDto.class,lambdaWrapper);
  }

  @Override
  public List<ProductVo> search(ProductQueryDto queryDto) {
    ProductQueryDto query =
        Optional.ofNullable(queryDto).orElse(new ProductQueryDto());
    MPJLambdaWrapper<Product> wrapper = new MPJLambdaWrapper<>();
    wrapper.like(
        StringUtils.isNotEmpty(query.getProductName()),
        Product::getName,
        query.getProductName());

    wrapper.like(
        StringUtils.isNotEmpty(query.getProductName()),
        Product::getName,
        query.getProductName());

    wrapper.like(
        StringUtils.isNotEmpty(query.getTypeId().toString()),
        Product::getTypeId,
        query.getTypeId());

    return productMapper.selectJoinList(ProductVo.class, wrapper);
  }

  @Override
  public ProductVo getById(Integer id) {
    ProductVo productVo = productMapper.selectJoinOne(ProductVo.class,
        new MPJLambdaWrapper<Product>()
            .eq(Product::getId, id));
    String image = getOne(new LambdaQueryWrapper<Product>()
        .eq(Product::getId, id)).getImage();

    List<String> str2List = StringUtils.str2List(image, ",", true, true);
    productVo.setImages(str2List);
    return productVo;
  }




}




