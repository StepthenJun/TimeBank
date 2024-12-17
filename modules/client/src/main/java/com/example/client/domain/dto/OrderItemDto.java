package com.example.client.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @description:
 * @author：StephenJun
 * @date: 2024/11/27
 * @email: 2398627868@qq.com
 */
@Data
public class OrderItemDto {
  private Integer id;

  private Integer orderId;

  private Integer productId;

  private Integer quantity;

  private Double price;
}
