package com.example.client.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: TimeBank
 * @ClassName: RegisterDto
 * @description:
 * @author: kai
 * @create: 2024-10-26 23:11
 */
@Data
public class RegisterDto {
    private Long schoolId;
    private Long academyId;
    private String studentNum;
    private String password;
    private MultipartFile avatar;
}
