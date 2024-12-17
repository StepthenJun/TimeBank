package com.example.client.service;

import com.example.client.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.client.domain.dto.OrderItemDto;
import java.util.List;

/**
* @author 86187
* @description 针对表【order】的数据库操作Service
* @createDate 2024-11-27 21:54:07
*/
public interface OrderService extends IService<Order> {


  void updateOrderStatus(Integer orderId, String status);

  void confirmOrder(Integer orderId, String password);

  void cancelOrder(Integer orderId);

  void refundOrder(Integer orderId);
}
