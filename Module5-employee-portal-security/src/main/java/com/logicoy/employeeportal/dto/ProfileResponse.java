package com.logicoy.employeeportal.dto;


//DTO used to expose employee profile details safely to the client without sensitive data

public class ProfileResponse {

    private Long id;
    private String username;
    private String role;
    private boolean enabled;

    public ProfileResponse(Long id, String username, String role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
