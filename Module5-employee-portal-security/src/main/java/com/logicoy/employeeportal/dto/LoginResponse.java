package com.logicoy.employeeportal.dto;

//DTO used to return the generated JWT token to the client after successful login
public class LoginResponse {
    private final String token;
    public LoginResponse(String token) {
        this.token = token;
    }
    public String getToken() { return token; }
}