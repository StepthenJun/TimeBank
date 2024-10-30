package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存储学院信息(Colleges)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colleges  {
    //学院ID@TableId
    private Integer collegeId;

    //学院名称
    private String collegeName;
    //学院描述
    private String description;



}

