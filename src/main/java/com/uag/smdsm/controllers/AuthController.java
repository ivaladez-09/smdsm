package com.uag.smdsm.controllers;

import com.uag.smdsm.entities.User;
import com.uag.smdsm.models.JwtAuthResponse;
import com.uag.smdsm.models.LoginDto;
import com.uag.smdsm.models.SignUpDto;
import com.uag.smdsm.repositories.RoleRepository;
import com.uag.smdsm.repositories.UserRepository;
import com.uag.smdsm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate() {
        return new ResponseEntity<String>("authenticated", HttpStatus.OK);
    }

    @GetMapping("/test-auth")
    public ResponseEntity<String> testAuthentication() {
        return new ResponseEntity<String>("authenticated", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword());
        var authentication = authenticationManager.authenticate(usernamePasswordToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>(new JwtAuthResponse(token, "JWT"), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken.", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken.", HttpStatus.BAD_REQUEST);
        }

        var user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setBirthday(LocalDate.parse(signUpDto.getBirthday()));

        var role = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return new ResponseEntity<>("User registered succesfully", HttpStatus.OK);
    }
}
