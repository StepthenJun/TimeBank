package com.example.client.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import lombok.Data;

/**
 * @description:
 * @author：StephenJun
 * @date: 2024/11/25
 * @email: 2398627868@qq.com
 */
@Data
public class ProductDto {
  private Integer id;

  private String name;

  private String image;

  private Double price;

  private String description;

  private String address;

  private String createBy;

  private Date createTime;
}
