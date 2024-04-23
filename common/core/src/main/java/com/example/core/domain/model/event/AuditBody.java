package com.example.core.domain.model.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode
public class AuditBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 需求id
     */

    private List<Long> idList;

    /**
     * 审核人
     */
//    private String auditManager;

    /**
     * 是否通过
     */

    private Integer status;

    /**
     * 失败原因
     */
    private String failReason;



}
