
package com.example.client.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 课程对象 course
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
@Data
public class Course
{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 课程id */
    private Long id;

    /** 课程资料url */

    private String url;

    /** 课程名称 */

    private String name;

    /** 课程描述 */

    private String description;

    /** 未审核0，已审核1 */

    private String status;

    /** 未删除0，已删除1 */
    private String delFlag;
    private Long createBy;

    private Integer price;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("url", getUrl())
            .append("name", getName())

            .append("description", getDescription())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
