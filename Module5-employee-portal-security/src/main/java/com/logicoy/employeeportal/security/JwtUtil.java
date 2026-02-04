package com.logicoy.employeeportal.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// Utility class responsible for generating and validating JWT tokens
@Component
public class JwtUtil {

    // Secret key used to sign and verify JWT tokens (loaded from application config)
    @Value("${jwt.secret}")
    private String secret;

    // Generates a JWT token containing the username as subject and role as a claim.
    
    public String generateToken(String username, String role) {
        return Jwts.builder()
            .setSubject(username)              // Identifies the user (WHO)
            .claim("role", role)               // Stores user role for authorization (WHAT)
            .setIssuedAt(new Date())            // Token creation time
            .setExpiration(
                new Date(System.currentTimeMillis() + 3600000)
            )                                  // Token expiry (1 hour)
            .signWith(
                Keys.hmacShaKeyFor(secret.getBytes())
            )                                  // Signs token using HMAC-SHA256
            .compact();                        // Builds final JWT string
    }

    //Validates the JWT signature and extracts all claims from the token Throws an exception if the token is invalid or expired.
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secret.getBytes())  // Uses same secret to verify signature
            .build()
            .parseClaimsJws(token)             // Parses and validates the token
            .getBody();                        // Returns token payload (claims)
    }
}
