package com.example.core.domain.pojos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 用户资料表(UserProfile)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile  {
    @TableId
    private Integer userId;

    //用户姓名
    private String name;
    //用户年龄
    private Integer age;
    //用户性别，未指定格式
    private Integer gender;
    //用户生日
    private Date birthday;
    //学校ID，关联具体学校
    private Integer schoolId;
    //专业ID，关联具体专业
    private Integer majorId;
    //年级ID，关联具体年级
    private Integer gradeId;
    //当前教育阶段描述
    private String currentEducation;
    //所属学院名称
    private String academy;
    //兴趣爱好
    private String hobbies;
    //学号，唯一标识
    private String studentNum;
    //个人描述
    private String description;



}

