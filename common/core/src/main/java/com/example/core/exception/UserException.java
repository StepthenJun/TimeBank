package com.example.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误码对应的参数
     */
    private Object[] args;
    public UserException(String code, Object... args) {
        this.code = code;
        this.args = args;
    }
}
