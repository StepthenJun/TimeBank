package com.example.core.domain.pojos;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 存储班级信息(Classes)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:41
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classes  {
    //班级ID@TableId
    private Integer classId;

    //所属专业ID，外键
    private Integer majorId;
    //班级名称
    private String className;



}

