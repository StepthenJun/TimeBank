package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.OrderItem;
import com.example.client.service.OrderItemService;
import com.example.client.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【order_item】的数据库操作Service实现
* @createDate 2024-11-27 21:54:07
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




