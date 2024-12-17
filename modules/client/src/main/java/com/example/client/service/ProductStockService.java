package com.example.client.service;

import com.example.redis.util.RedisUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductStockService {

    private static final String PRODUCT_STOCK_KEY = "product:stock:"; // Redis库存Key前缀

    /**
     * 初始化商品库存到Redis
     */
    public void initStockToRedis(Integer productId, Integer stock) {
        RedisUtils.setAtomicValue(PRODUCT_STOCK_KEY + productId, stock);
    }

    /**
     * 扣减Redis库存
     */
    public boolean deductStock(Integer productId, Integer quantity) {
        long stock = RedisUtils.decrAtomicValue(PRODUCT_STOCK_KEY + productId);
        if (stock < 0) {
            // 如果库存不足，回滚 Redis 变更
            RedisUtils.incrAtomicValue(PRODUCT_STOCK_KEY + productId);
            return false;
        }
        return true;
    }

    /**
     * 增加Redis库存（用于退款或取消订单）
     */
    public void addStock(Integer productId, Integer quantity) {
        RedisUtils.incrAtomicValue(PRODUCT_STOCK_KEY + productId);
    }

    /**
     * 获取商品库存
     */
    public long getStock(Integer productId) {
        return RedisUtils.getAtomicValue(PRODUCT_STOCK_KEY + productId);
    }
}
