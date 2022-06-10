package com.uag.smdsm.models;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
