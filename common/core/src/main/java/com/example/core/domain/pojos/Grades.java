package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存储年级信息(Grades)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grades  {
    //年级ID@TableId
    private Integer gradeId;

    //所属学院ID，外键
    private Integer collegeId;
    //年级名称
    private String gradeName;



}

