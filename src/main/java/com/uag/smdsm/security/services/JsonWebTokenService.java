package com.uag.smdsm.security.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JsonWebTokenService {
    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-milliseconds}")
    private Integer expirationMilliseconds;

    public String generate(Authentication authentication) {
        var username = authentication.getName();
        var currentDate = new Date();
        var expireDate = new Date(currentDate.getTime() + expirationMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsername(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }

    public boolean isValid(String jwt) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt);
        return true;
    }
}
