package com.uag.smdsm.security.controllers;

import com.uag.smdsm.security.models.*;
import com.uag.smdsm.security.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signin(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.authenticateUser(loginDto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpDto signUpDto) {
        authService.registerUser(signUpDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
