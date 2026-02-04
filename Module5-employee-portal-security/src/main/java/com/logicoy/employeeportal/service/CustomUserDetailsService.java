package com.logicoy.employeeportal.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.logicoy.employeeportal.model.Employee;
import com.logicoy.employeeportal.repository.EmployeeRepository;

// Loads user details from the database for authentication
@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final EmployeeRepository repo;

    public CustomUserDetailsService(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        // Fetch user from DB or fail authentication if not found
        Employee emp = repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
            .username(emp.getUsername())
            .password(emp.getPassword())
            // Remove ROLE_ prefix since Spring adds it automatically
            .roles(emp.getRole().name().replace("ROLE_", ""))
            // Disable login if user is marked inactive
            .disabled(!emp.isEnabled())
            .build();
    }
}
