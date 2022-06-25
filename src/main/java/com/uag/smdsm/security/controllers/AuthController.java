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
@CrossOrigin
public class AuthController {
    private final AuthService service;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signin(@RequestBody Login login) {
        return new ResponseEntity<>(service.authenticateUser(login), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUp signUp) {
        service.registerUser(signUp);
        return new ResponseEntity<>("User registered", HttpStatus.OK);
    }
}
