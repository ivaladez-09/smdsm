package com.uag.smdsm.api.services;

import com.uag.smdsm.api.entities.User;
import com.uag.smdsm.api.exceptions.ResourceNotFoundException;
import com.uag.smdsm.api.mappers.UserMapper;
import com.uag.smdsm.api.models.UserResponse;
import com.uag.smdsm.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found."));
        return userMapper.toUserResponse(user);
    }

    public Integer countByGenderAndRiskFactor(String gender, String riskFactor) {
        return userRepository.countByGenderAndRiskFactor(gender, riskFactor);
    }

    public Integer countByGenderAndDisease(String gender, String disease) {
        return userRepository.countByGenderAndDisease(gender, disease);
    }

    public Integer countByGenderAndBirthdayBetween(String gender, String startDate, String endDate) {
        return userRepository.countByGenderAndBirthdayBetween(
                gender,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
    }
}
