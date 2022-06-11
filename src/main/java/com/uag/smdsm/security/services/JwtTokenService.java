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
public class JwtTokenService {
    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-milliseconds}")
    private Integer expirationMilliseconds;

    public String generateToken(Authentication authentication) {
        var username = authentication.getName();
        var currentDate = new Date();
        var expireDate = new Date(currentDate.getTime() + expirationMilliseconds);

        var token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return token;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw e;
        }

    }
}
