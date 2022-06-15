package com.uag.smdsm.security.services;

import com.uag.smdsm.security.entities.SpringRole;
import com.uag.smdsm.security.mappers.AuthMapper;
import com.uag.smdsm.security.models.JwtAuthResponse;
import com.uag.smdsm.security.models.LoginDto;
import com.uag.smdsm.security.models.SignUpDto;
import com.uag.smdsm.security.repositories.SpringRoleRepository;
import com.uag.smdsm.security.repositories.SpringUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Collections;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final SpringUserRepository springUserRepository;
    private final SpringRoleRepository springRoleRepository;
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
        if (springUserRepository.existsByUsername(signUpDto.getUsername()) ||
                springUserRepository.existsByEmail(signUpDto.getEmail())) {
            throw new BadCredentialsException("Credentials already taken.");
        }

        var user = authMapper.toSpringUser(signUpDto);
        var role = springRoleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    var userRole = new SpringRole();
                    userRole.setName("ROLE_USER");
                    return userRole;
                });
        user.setRoles(Collections.singletonList(role));

        springUserRepository.save(user);
    }
}
