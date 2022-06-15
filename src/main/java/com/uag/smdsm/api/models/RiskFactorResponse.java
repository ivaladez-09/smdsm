package com.uag.smdsm.api.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RiskFactorResponse {
    private String name;
    private Boolean isActive;
    private Integer rate;
}
