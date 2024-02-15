package com.example.client.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventVo {
    /**
     * 需求描述
     */
    private String description;

    /**
     * 需求人数
     */
    private Integer needNum;

    /**
     * 地址信息
     */
    private String address;

    /**
     * 发布状态默认为’0‘（未审核0，不可用2，可用3，已完成）
     */
    private String status;

    /**
     * 未删除0，已删除1
     */
    private String delFlag;

    /**
     * 奖励时间币的个数
     */
    private Integer reward;

}
