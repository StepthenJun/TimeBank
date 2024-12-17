package com.example.client.domain;

import lombok.Data;

// 创建Message对象
@Data
public class Message {
    private String sender;
    private String content;
    private String time;
  }