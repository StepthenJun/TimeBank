package com.example.core.domain.pojos;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 用户联系人关系表(Contacts)表实体类
 *
 * @author makejava
 * @since 2024-10-24 19:33:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contacts  {
    @TableId
    private Integer contactId;

    //用户ID，关联user_profile表
    private Integer userId;
    //联系人的用户ID，同样关联user_profile表
    private Integer contactUserId;
    //联系人关系状态（如active, pending, blocked）
    private String status;
    //创建时间
    private Date createdAt;



}

