package com.example.core.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginBody implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 授权类型
     */
    @NotBlank()
    private String grantType;
    /**
     * 唯一标识
     */
    private String uuid;

}