package com.example.core.domain.model.user;

import lombok.Data;

/**
 * @program: TimeBank
 * @ClassName: UpdateInfoBody
 * @description:
 * @author: kai
 * @create: 2024-04-22 23:17
 */
@Data
public class UpdateInfoBody {
    private Long id;
    private String userName;
    private String password;
    private String address;
    private String phonenumber;

}
