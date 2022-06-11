package com.uag.smdsm.security.mappers;

import com.uag.smdsm.security.entities.SpringUser;
import com.uag.smdsm.security.models.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AuthMapper {
    private final PasswordEncoder passwordEncoder;

    public SpringUser toSpringUser(SignUpDto signUpDto) {
        return SpringUser.builder()
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
    }
}
