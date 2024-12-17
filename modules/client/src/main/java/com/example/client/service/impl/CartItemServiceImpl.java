package com.example.client.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.CartItem;
import com.example.client.domain.Product;
import com.example.client.mapper.ProductMapper;
import com.example.client.service.CartItemService;
import com.example.client.mapper.CartItemMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【cart_item】的数据库操作Service实现
* @createDate 2024-11-27 22:07:03
*/
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem>
    implements CartItemService{
  @Autowired
  private CartItemMapper cartItemMapper;

  @Autowired
  private ProductMapper productMapper;

  /**
   * 添加商品到购物车
   */
  public void addToCart(Integer productId, Integer quantity) {
    // 检查商品是否存在
    Product product = productMapper.selectById(productId);
    if (product == null) {
      throw new RuntimeException("商品不存在");
    }

    // 检查购物车中是否已存在该商品
    LambdaQueryWrapper<CartItem> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(CartItem::getUserId, StpUtil.getLoginIdAsInt())
            .eq(CartItem::getProductId,productId);
    CartItem cartItem = cartItemMapper.selectOne(queryWrapper);

    if (cartItem == null) {
      // 购物车中不存在该商品，新增
      cartItem = new CartItem();
      cartItem.setUserId(StpUtil.getLoginIdAsInt());
      cartItem.setProductId(productId);
      cartItem.setQuantity(quantity);
      cartItemMapper.insert(cartItem);
    } else {
      // 购物车中已存在该商品，更新数量
      cartItem.setQuantity(cartItem.getQuantity() + quantity);
      cartItemMapper.updateById(cartItem);
    }
  }

  /**
   * 更新购物车中商品的数量
   */
  public void updateCartItem(Integer productId, Integer quantity) {
    LambdaQueryWrapper<CartItem> queryWrapper = new LambdaQueryWrapper<>();
//    queryWrapper.eq("user_id", userId).eq("product_id", productId);
    queryWrapper.eq(CartItem::getUserId,StpUtil.getLoginIdAsInt())
        .eq(CartItem::getProductId,productId);
    CartItem cartItem = cartItemMapper.selectOne(queryWrapper);
    if (cartItem == null) {
      throw new RuntimeException("购物车中不存在该商品");
    }

    if (quantity <= 0) {
      // 数量小于等于0，删除该商品
      cartItemMapper.deleteById(cartItem.getId());
    } else {
      // 更新数量
      cartItem.setQuantity(quantity);
      cartItemMapper.updateById(cartItem);
    }
  }

  /**
   * 删除购物车中的商品
   */
  public void removeCartItem(Integer userId, Integer productId) {
    QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("user_id", userId).eq("product_id", productId);
    cartItemMapper.delete(queryWrapper);
  }

  /**
   * 获取用户的购物车列表
   */
  public List<CartItem> getCartItems(Integer userId) {
    QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("user_id", userId);
    return cartItemMapper.selectList(queryWrapper);
  }
}




