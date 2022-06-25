package com.uag.smdsm.security.models;

import lombok.Data;

@Data
public class Login {
    private String usernameOrEmail;
    private String password;
}
