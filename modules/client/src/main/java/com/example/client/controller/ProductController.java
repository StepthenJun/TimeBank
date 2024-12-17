package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.example.client.domain.dto.ProductDto;
import com.example.client.domain.dto.ProductQueryDto;
import com.example.client.domain.vo.ProductVo;
import com.example.client.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@SaIgnore
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  /**
   * 获取商品列表
   * @return 商品列表
   */
  @GetMapping("/list")
  public List<ProductVo> getList() {
    log.info("获取商品列表");
    return productService.getList();
  }

  /**
   * 获取商品详情
   * @param id 商品ID
   * @return 商品详情
   */
  @GetMapping("/detail/{id}")
  public ProductVo getById(@PathVariable Integer id) {
    log.info("获取商品详情，ID：{}", id);
    return productService.getById(id);
  }

  /**
   * 根据分类标签获取商品列表
   * @param typeId 分类ID
   * @return 商品列表
   */
  @GetMapping("/list/type/{typeId}")
  public List<ProductDto> getListByType(@PathVariable Integer typeId) {
    log.info("根据分类ID获取商品列表，分类ID：{}", typeId);
    return productService.getListByType(typeId);
  }

  /**
   * 根据关键词模糊搜索商品
   * @param queryDto 搜索条件
   * @return 商品列表
   */
  @PostMapping("/search")
  public List<ProductVo> search(@RequestBody ProductQueryDto queryDto) {
    log.info("根据关键词搜索商品，条件：{}", queryDto);
    return productService.search(queryDto);
  }

}
