package com.uag.smdsm.security.mappers;

import com.uag.smdsm.security.entities.SpringUser;
import com.uag.smdsm.security.models.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AuthMapper {
    private final PasswordEncoder passwordEncoder;

    public SpringUser toSpringUser(SignUp signUp) {
        return SpringUser.builder()
                .username(signUp.getUsername())
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .build();
    }
}
