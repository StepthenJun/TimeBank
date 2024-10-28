package com.example.core.domain.pojos;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 存储专业信息(Majors)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Majors  {
    //专业ID@TableId
    private Integer majorId;

    //所属年级ID，外键
    private Integer gradeId;
    //专业名称
    private String majorName;



}

