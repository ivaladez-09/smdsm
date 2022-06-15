package com.uag.smdsm.api.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastname;
    private String gender;
    private String birthday;
    private List<DiseaseResponse> diseases;
    private List<RiskFactorResponse> riskFactors;
}
