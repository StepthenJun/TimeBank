package com.example.core.domain.model.audit;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode
public class ApprovalBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private List<Long> idList;

    /**
     * 状态
     */
    private Integer status;
}
