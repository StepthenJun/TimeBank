package com.example.client.domain.vo;

import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author：StephenJun
 * @date: 2024/11/27
 * @email: 2398627868@qq.com
 */
@Data
public class ProductVo {
  private Integer id;

  private String name;

  private List<String> images;

  private Double price;

  private String description;

  private String address;

  private String createBy;

  private Date createTime;

  private String type;
}
