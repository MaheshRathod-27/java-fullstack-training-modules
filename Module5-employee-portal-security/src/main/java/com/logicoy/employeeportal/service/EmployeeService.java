package com.logicoy.employeeportal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.logicoy.employeeportal.dto.ProfileResponse;
import com.logicoy.employeeportal.dto.RegisterRequest;
import com.logicoy.employeeportal.model.Employee;
import com.logicoy.employeeportal.model.Role;
import com.logicoy.employeeportal.repository.EmployeeRepository;

@Service
public class EmployeeService {

    // Logger to track registration flow and security-related events
    private static final Logger log =
        LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository repo;
    private final PasswordEncoder encoder;

    public EmployeeService(EmployeeRepository repo,
                           PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // Register a new employee
    public void register(RegisterRequest req) {

        log.info("Processing registration for username: {}", req.getUsername());

        // Prevent duplicate usernames 
        if (repo.existsByUsername(req.getUsername()))
        {
            log.warn("Duplicate registration attempt detected for username: {}",
                     req.getUsername());
            throw new IllegalArgumentException("Username already exists");
        }

        // Validate and convert request role to a defined Role enum constant
        Role role = Role.valueOf(req.getRole().toUpperCase());

        log.debug("Resolved role for username {}: {}", req.getUsername(), role);

        // Create Employee entity with encoded password
        Employee emp = new Employee(
            req.getUsername(),
            encoder.encode(req.getPassword()), // password is NEVER stored raw
            role
        );

        // Persist employee in database
        repo.save(emp);

        log.info("User registered successfully with username: {}",
                 req.getUsername());
    }

    // Fetch logged-in employee profile (business logic)
   
    public ProfileResponse getProfile(String username) {

        log.info("Fetching profile details for username: {}", username);

        // Fetch employee from database using username from JWT
        Employee emp = repo.findByUsername(username)
                .orElseThrow(() -> {
                    // This can happen if user was deleted after JWT was issued
                    log.error("Profile access failed - user not found: {}", username);
                    return new RuntimeException("User not found");
                });

        // Check if user account is disabled
        if (!emp.isEnabled()) {
            log.warn("Disabled user attempted to access profile: {}", username);
            throw new RuntimeException("User account is disabled");
        }

        // Convert Employee entity to ProfileResponse DTO
        ProfileResponse response = new ProfileResponse(
                emp.getId(),
                emp.getUsername(),
                emp.getRole().name(),
                emp.isEnabled()
        );

        log.info("Profile successfully retrieved for username: {}", username);

        return response;
    }
    
 // Manager-only business logic
    public List<ProfileResponse> getAllEmployees() {

        log.info("Manager requested list of all employees");

        return repo.findAll()
                .stream()
                .map(emp -> new ProfileResponse(
                        emp.getId(),
                        emp.getUsername(),
                        emp.getRole().name(),
                        emp.isEnabled()
                ))
                .collect(Collectors.toList());
    }
}
