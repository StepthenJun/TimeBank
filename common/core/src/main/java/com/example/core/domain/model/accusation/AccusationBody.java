package com.example.core.domain.model.accusation;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode
public class AccusationBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "原因不能为空")
    private String reason;
    @NotBlank(message = "描述不能为空")
    private String content;
    @NotBlank(message = "材料不能为空")
    private MultipartFile[] multipartFiles;
}
