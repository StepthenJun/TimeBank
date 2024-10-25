package com.example.core.domain.model;


import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class AddressBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private Double eventLat;
    @NotBlank
    private Double eventLon;
    @NotBlank
    private Double userLat;
    @NotBlank
    private Double userLon;

    private Long eventId;
}
