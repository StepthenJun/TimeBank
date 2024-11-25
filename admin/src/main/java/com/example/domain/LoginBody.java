package com.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @description:
 * @author：StephenJun
 * @date: 2024/11/25
 * @email: 2398627868@qq.com
 */

@Data
public class LoginBody {
  @Pattern(regexp = "/d*")
  @JsonProperty("phone_num")
  private String phoneNum;

  private String password;
}
