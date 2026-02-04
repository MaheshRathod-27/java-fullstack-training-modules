package com.logicoy.employeeportal.security;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

// Security filter that validates JWT tokens and sets authentication for each request
@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws IOException, ServletException {

        // Extract Authorization header from incoming request
        String header = req.getHeader("Authorization");

        // If no JWT is present, skip authentication and continue the filter chain
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        // Remove "Bearer " prefix and parse JWT claims
        String token = header.substring(7);
        Claims claims = jwtUtil.extractClaims(token);

        // Build Authentication object using username (subject) and role from JWT
        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                List.of(
                    new SimpleGrantedAuthority(
                        claims.get("role").toString()
                    )
                )
            );

        // Store authentication in SecurityContext for authorization checks
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Continue processing the remaining filters and eventually the controller
        chain.doFilter(req, res);
    }
}
