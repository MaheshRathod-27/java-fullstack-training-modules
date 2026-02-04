package com.logicoy.employeeportal.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.logicoy.employeeportal.model.Employee;
import com.logicoy.employeeportal.repository.EmployeeRepository;
@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final EmployeeRepository repo;

    public CustomUserDetailsService(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Employee emp = repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
            .username(emp.getUsername())
            .password(emp.getPassword())
            .roles(emp.getRole().name().replace("ROLE_", ""))
            .disabled(!emp.isEnabled())
            .build();
    }
}
