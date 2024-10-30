package com.example.core.domain.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 社区信息表(Communities)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Communities  {
    @TableId
    private Integer communityId;

    //社区名称
    private String communityName;
    //社区类型，未详细说明具体类型
    private Integer tagId;
    //社区描述
    private String description;



}

