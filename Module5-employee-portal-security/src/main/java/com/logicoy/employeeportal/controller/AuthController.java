package com.logicoy.employeeportal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.logicoy.employeeportal.dto.LoginRequest;
import com.logicoy.employeeportal.dto.LoginResponse;
import com.logicoy.employeeportal.dto.RegisterRequest;
import com.logicoy.employeeportal.security.JwtUtil;
import com.logicoy.employeeportal.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log =
            LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final EmployeeService service;

    //constructor injection
    public AuthController(AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          EmployeeService service) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

 
     //Creates a user after validating input and encoding the password.
     // JWT is NOT issued here; authentication happens via /login.
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest req) {

        log.info("Register request received for username: {}", req.getUsername());

        service.register(req);

        log.info("User registered successfully: {}", req.getUsername());
        return "User registered";
    }

    
//      Authenticates username & password using AuthenticationManager.
//      On success, generates a JWT containing username and role.
     
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {

        log.info("Login attempt for username: {}", req.getUsername());

        // authenticate() validates credentials via UserDetailsService
        Authentication auth =
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    req.getUsername(), req.getPassword()
                )
            );

        // Extract role from authenticated principal for JWT claim
        String role =
            auth.getAuthorities().iterator().next().getAuthority();

        log.debug("Authentication successful for user: {} with role: {}",
                  req.getUsername(), role);

        // JWT contains identity + role; used for stateless authorization
        String token = jwtUtil.generateToken(req.getUsername(), role);

        log.info("JWT issued for user: {}", req.getUsername());

        return new LoginResponse(token);
    }
}
