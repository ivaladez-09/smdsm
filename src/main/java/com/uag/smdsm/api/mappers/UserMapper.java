package com.uag.smdsm.api.mappers;

import com.uag.smdsm.api.entities.User;
import com.uag.smdsm.api.entities.UserDisease;
import com.uag.smdsm.api.entities.UserRiskFactor;
import com.uag.smdsm.api.models.DiseaseResponse;
import com.uag.smdsm.api.models.RiskFactorResponse;
import com.uag.smdsm.api.models.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        List<DiseaseResponse> diseases = new ArrayList<>();
        List<RiskFactorResponse> riskFactors = new ArrayList<>();

        diseases = user.getDiseases().stream()
                .map(this::fromUserDisease)
                .collect(Collectors.toList());

        riskFactors = user.getRiskFactors().stream()
                .map(this::fromUserRiskFactor)
                .collect(Collectors.toList());

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastname(user.getLastName())
                .gender(user.getGender())
                .birthday(user.getBirthday().toString())
                .diseases(diseases)
                .riskFactors(riskFactors)
                .build();
    }

    public DiseaseResponse fromUserDisease(UserDisease userDisease) {
        return DiseaseResponse.builder()
                .name(userDisease.getDisease().getName())
                .isActive(userDisease.getIsActive())
                .build();
    }

    public RiskFactorResponse fromUserRiskFactor(UserRiskFactor userRiskFactor) {
        return RiskFactorResponse.builder()
                .name(userRiskFactor.getRiskFactor().getName())
                .isActive(userRiskFactor.getIsActive())
                .rate(userRiskFactor.getRate())
                .build();
    }
}
