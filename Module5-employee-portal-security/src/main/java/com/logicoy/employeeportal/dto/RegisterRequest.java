package com.logicoy.employeeportal.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
	  @NotBlank
	    private String username;

	    @NotBlank
	    private String password;

	    @NotBlank
	    private String role; // EMPLOYEE / MANAGER

	    public String getUsername(){
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getRole() {
	        return role;
	    }

}
