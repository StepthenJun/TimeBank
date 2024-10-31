package com.example.client.model.dto;

import lombok.Data;

/**
 * @description:
 * @author：StephenJun
 * @date: 2024/10/30
 * @email: 2398627868@qq.com
 */
@Data
public class ChatDto {
  private Long senderId;
  private Long receiverId;
  private String message;
}
