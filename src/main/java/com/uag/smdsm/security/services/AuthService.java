package com.uag.smdsm.security.services;

import com.uag.smdsm.security.mappers.AuthMapper;
import com.uag.smdsm.security.models.JwtAuthResponse;
import com.uag.smdsm.security.models.LoginDto;
import com.uag.smdsm.security.models.SignUpDto;
import com.uag.smdsm.security.repositories.RoleRepository;
import com.uag.smdsm.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthMapper authMapper;

    public JwtAuthResponse authenticateUser(LoginDto loginDto) {
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword());

        var authentication = authenticationManager.authenticate(usernamePasswordToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var token = jwtTokenService.generateToken(authentication);
        return new JwtAuthResponse(token, "JWT");
    }

    public void registerUser(SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername()) ||
                userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new BadCredentialsException("Credentials already taken.");
        }

        var user = authMapper.toUser(signUpDto);
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").get()));

        userRepository.save(user);
    }
}
