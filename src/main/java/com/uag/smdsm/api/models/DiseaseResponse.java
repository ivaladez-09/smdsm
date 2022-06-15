package com.uag.smdsm.api.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DiseaseResponse {
    private String name;
    private Boolean isActive;
}
