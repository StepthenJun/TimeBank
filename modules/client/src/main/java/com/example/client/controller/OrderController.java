package com.example.client.controller;

import com.example.client.domain.Order;
import com.example.client.service.OrderService;
import com.example.client.service.VirtualWalletService;
import com.example.core.domain.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/confirm/{orderId}")
    public R<String> confirmOrder(@PathVariable Integer orderId, @RequestParam String password) {
        orderService.confirmOrder(orderId, password);
        return R.ok("订单支付成功");
    }

    @PostMapping("/cancel/{orderId}")
    public R<String> cancelOrder(@PathVariable Integer orderId) {
        orderService.cancelOrder(orderId);
        return R.ok("订单已取消");
    }

    @PostMapping("/refund/{orderId}")
    public R<String> refundOrder(@PathVariable Integer orderId) {
        orderService.refundOrder(orderId);
        return R.ok("订单退款成功");
    }
}
