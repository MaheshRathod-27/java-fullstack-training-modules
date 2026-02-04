package com.logicoy.employeeportal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.logicoy.employeeportal.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Logger to track security configuration lifecycle
    private static final Logger log =
            LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * PasswordEncoder bean
     * Uses BCrypt hashing algorithm to securely encode passwords
     * before storing them in the database.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        log.info("Initializing BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager bean
     * --------------------------
     * Delegates authentication to Spring Security’s internal configuration,
     * which uses UserDetailsService and PasswordEncoder.
     * This is required for login authentication.
     */
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        log.info("Exposing AuthenticationManager bean");
        return config.getAuthenticationManager();
    }

    /**
     * SecurityFilterChain configuration
     * ---------------------------------
     * 1. Disables CSRF because this is a stateless REST API using JWT
     * 2. Configures stateless session management (no HTTP session)
     * 3. Defines role-based authorization rules
     * 4. Adds JWT filter before UsernamePasswordAuthenticationFilter
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                   JwtAuthenticationFilter jwtFilter)
            throws Exception {

        log.info("Configuring SecurityFilterChain");

        http
            // CSRF is disabled because JWT is sent via Authorization header
            .csrf(csrf -> csrf.disable())

            // Stateless session management since JWT is used
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Authorization rules based on roles
            .authorizeHttpRequests(auth -> auth
                // Public endpoints: register & login
                .requestMatchers("/auth/**").permitAll()

                // Only EMPLOYEE role can access employee APIs
                .requestMatchers("/employee/**").hasAuthority("ROLE_EMPLOYEE")

                // Only MANAGER role can access manager APIs
                .requestMatchers("/manager/**").hasAuthority("ROLE_MANAGER")

                // Any other request must be authenticated
                .anyRequest().authenticated()
            )

            // JWT filter validates token before Spring’s authentication filter
            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        log.info("SecurityFilterChain configured successfully");
        return http.build();
    }
}
