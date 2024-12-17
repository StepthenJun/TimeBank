package com.example.client.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Order;
import com.example.client.domain.OrderItem;
import com.example.client.domain.Product;
import com.example.client.domain.dto.OrderItemDto;
import com.example.client.mapper.OrderItemMapper;
import com.example.client.mapper.ProductMapper;
import com.example.client.service.OrderService;
import com.example.client.mapper.OrderMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【order】的数据库操作Service实现
* @createDate 2024-11-27 21:54:07
*/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{
  private final ProductMapper productMapper;
  private final OrderMapper orderMapper;
  private final OrderItemMapper orderItemMapper;

  @Override
  public Order createOrder(List<OrderItemDto> items) {
    // 校验用户和商品
    double totalAmount = 0;
    for (OrderItemDto item : items) {
      Product product = productMapper.selectById(item.getProductId());
      if (product == null || item.getQuantity() > product.getStock()) {
        throw new RuntimeException("商品库存不足或商品不存在");
      }
      totalAmount += product.getPrice() * item.getQuantity();
    }

    // 创建订单
    Order order = new Order();
    order.setUserId(StpUtil.getLoginIdAsInt());
    order.setTotalAmount(totalAmount);
    order.setStatus("PENDING");
    orderMapper.insert(order);

    // 创建订单明细
    for (OrderItemDto item : items) {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrderId(order.getId());
      orderItem.setProductId(item.getProductId());
      orderItem.setQuantity(item.getQuantity());
      orderItem.setPrice(item.getPrice());
      orderItemMapper.insert(orderItem);

      // 减库存
      Product product = productMapper.selectById(item.getProductId());
      product.setStock(product.getStock() - item.getQuantity());
      productMapper.updateById(product);
  }
    return order;
  }


}




