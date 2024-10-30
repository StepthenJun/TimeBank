package com.example.core.domain.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    //主键@TableId
    private Long id;

    //用户名
    private String userName;
    //密码
//    private String password;
//
//    private String type;
//
//    private String status;
//    //手机号
//    private String phonenumber;
//    //头像
//    private String avatar;
//    //删除标志（0代表未删除，1代表已删除）
//    private Integer delFlag;
//    //账号
//    private String account;
//
//    private String hobby;
//
//    private String awards;
//    //是否实名
//    private Integer isRealName;



}

