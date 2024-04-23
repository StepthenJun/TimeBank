package com.example.client.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName accusation
 */
@TableName(value ="accusation")
@NoArgsConstructor
@Data
public class Accusation implements Serializable {
    public Accusation(Long id, String content, Long createBy, Long createTo, String status, String reason) {
        this.id = id;
        this.content = content;
        this.createBy = createBy;
        this.createTo = createTo;
        this.status = status;
        this.reason = reason;
    }

    /**
     * 举报id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 举报信息
     */
    private String content;

    /**
     * 创建者id
     */
    private Long createBy;

    /**
     * 举报对象id（需求或者是志愿者）
     */
    private Long createTo;

    /**
     * 状态（未审核0，已审核1）
     */
    private String status;

    /**
     * 原因
     */
    private String reason;

    /**
     * 图片地址
     */
    private String imageUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Accusation other = (Accusation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTo() == null ? other.getCreateTo() == null : this.getCreateTo().equals(other.getCreateTo()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTo() == null) ? 0 : getCreateTo().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTo=").append(createTo);
        sb.append(", status=").append(status);
        sb.append(", reason=").append(reason);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}