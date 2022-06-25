package com.uag.smdsm.api.services;

import com.uag.smdsm.api.exceptions.ResourceNotFoundException;
import com.uag.smdsm.api.mappers.UserMapper;
import com.uag.smdsm.api.models.UserResponse;
import com.uag.smdsm.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.parse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toUserResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found."));
    }

    public Integer countByGenderAndRiskFactor(String gender, String riskFactor) {
        return repository.countByGenderAndRiskFactor(gender, riskFactor);
    }

    public Integer countByGenderAndDisease(String gender, String disease) {
        return repository.countByGenderAndDisease(gender, disease);
    }

    public Integer countByGenderAndBirthdayBetween(String gender, String startDate, String endDate) {
        return repository.countByGenderAndBirthdayBetween(gender, parse(startDate), parse(endDate)
        );
    }
}
