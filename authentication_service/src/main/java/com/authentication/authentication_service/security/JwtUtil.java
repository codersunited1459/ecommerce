package com.authentication.authentication_service.security;

import com.authentication.authentication_service.dto.UserDTO;
import com.authentication.authentication_service.model.UserAuthData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "mySecretKeymySecretKeymySecretKey";
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //SignatureAlgorithm.HS256
    private final long EXPIRATION = 1000 * 60 * 60;
    // Generate Token
    public String generateToken(UserAuthData user) {
        return Jwts.builder()
                .setSubject(user.getMobile())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }


    //  Validate JWT Token
    public boolean validateToken(String token, UserDTO userDTO) {
        try {
            final String mobile = extractMobileNumber(token);
            return (mobile.equals(userDTO.getMobile()) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }
    // Extract username from token
    public String extractMobileNumber(String token) {
        return extractAllClaims(token).getSubject();
    }

    //  Extract role from token
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    //  Check if token expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //  Extract all claims (with validation)
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}

