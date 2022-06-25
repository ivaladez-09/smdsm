package com.uag.smdsm.security.services;

import com.uag.smdsm.security.entities.SpringRole;
import com.uag.smdsm.security.mappers.AuthMapper;
import com.uag.smdsm.security.models.JwtAuthResponse;
import com.uag.smdsm.security.models.Login;
import com.uag.smdsm.security.models.SignUp;
import com.uag.smdsm.security.repositories.SpringRoleRepository;
import com.uag.smdsm.security.repositories.SpringUserRepository;
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
    private final SpringUserRepository springUserRepository;
    private final SpringRoleRepository springRoleRepository;
    private final JsonWebTokenService jwtService;
    private final AuthMapper authMapper;

    public JwtAuthResponse authenticateUser(Login login) {
        var userToken = new UsernamePasswordAuthenticationToken(login.getUsernameOrEmail(), login.getPassword());

        var authentication = authenticationManager.authenticate(userToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var jwt = jwtService.generate(authentication);
        return new JwtAuthResponse(jwt, "JWT");
    }

    public void registerUser(SignUp signUp) {
        if (springUserRepository.existsByUsername(signUp.getUsername()) ||
                springUserRepository.existsByEmail(signUp.getEmail())) {
            throw new BadCredentialsException("Credentials already taken.");
        }

        var user = authMapper.toSpringUser(signUp);
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
