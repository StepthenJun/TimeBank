package com.example.client.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Order;
import com.example.client.domain.OrderItem;
import com.example.client.domain.Product;
import com.example.client.domain.dto.OrderItemDto;
import com.example.client.mapper.OrderItemMapper;
import com.example.client.mapper.ProductMapper;
import com.example.client.service.OrderService;
import com.example.client.mapper.OrderMapper;
import com.example.client.service.ProductService;
import com.example.client.service.ProductStockService;
import com.example.client.service.VirtualWalletService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 86187
* @description 针对表【order】的数据库操作Service实现
* @createDate 2024-11-27 21:54:07
*/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

  private final ProductStockService stockService;
  private final OrderMapper orderMapper;
  private final VirtualWalletService walletService;

  @Transactional
  public void confirmOrder(Integer orderId, String paymentPassword) {
    Order order = orderMapper.selectById(orderId);
    if (!"待支付".equals(order.getStatus())) {
      throw new RuntimeException("订单状态不正确");
    }

    // 扣除钱包余额
    walletService.deductBalance(order.getUserId(), order.getTotalAmount(), paymentPassword);

    // 扣减Redis库存
    boolean success = stockService.deductStock(order.getProductId(), order.getQuantity());
    if (!success) {
      throw new RuntimeException("库存不足");
    }

    // 更新订单状态
    updateOrderStatus(orderId, "已支付");
  }

  @Transactional
  public void cancelOrder(Integer orderId) {
    Order order = orderMapper.selectById(orderId);
    if (!"待支付".equals(order.getStatus())) {
      throw new RuntimeException("订单状态不正确");
    }

    // 退还 Redis 库存
    stockService.addStock(order.getProductId(), order.getQuantity());

    // 更新订单状态
    updateOrderStatus(orderId, "已取消");
  }

  @Transactional
  public void refundOrder(Integer orderId) {
    Order order = orderMapper.selectById(orderId);
    if (!"已支付".equals(order.getStatus())) {
      throw new RuntimeException("订单状态不正确");
    }

    // 退还钱包余额
    walletService.refundBalance(order.getUserId(), order.getTotalAmount());

    // 增加 Redis 库存
    stockService.addStock(order.getProductId(), order.getQuantity());

    // 更新订单状态
    updateOrderStatus(orderId, "已退款");
  }


  @Override
  public void updateOrderStatus(Integer orderId, String status) {
    LambdaUpdateWrapper<Order> wrapper = new LambdaUpdateWrapper<>();
    wrapper.eq(Order::getId,orderId)
        .set(Order::getStatus,status);
    boolean update = update(wrapper);
    if (!update){
      throw new RuntimeException("失败");
    }
  }




}




