package com.uag.smdsm.security.mappers;

import com.uag.smdsm.security.entities.User;
import com.uag.smdsm.security.models.SignUpDto;
import com.uag.smdsm.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AuthMapper {
    private final PasswordEncoder passwordEncoder;

    public User toUser(SignUpDto signUpDto) {
        return User.builder()
                .name(signUpDto.getName())
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .birthday(LocalDate.parse(signUpDto.getBirthday()))
                .build();
    }
}
