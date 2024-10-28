package com.example.core.domain.pojos;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * (AuthIdCard)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthIdCard  {
    //主键@TableId
    private Long id;

    //实名
    private String realName;
    //身份证号
    private String idNumber;
    //用户id
    private Long userId;



}

