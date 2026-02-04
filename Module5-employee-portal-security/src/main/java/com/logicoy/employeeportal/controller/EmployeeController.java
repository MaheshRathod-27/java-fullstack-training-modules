package com.logicoy.employeeportal.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logicoy.employeeportal.dto.ProfileResponse;
import com.logicoy.employeeportal.model.Employee;
import com.logicoy.employeeportal.repository.EmployeeRepository;
import com.logicoy.employeeportal.service.EmployeeService;

@RestController
public class EmployeeController {
	   private final EmployeeService employeeService;

	    // Constructor-based dependency injection 
	    public EmployeeController(EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }
	    
	    //Accessible only by users with ROLE_EMPLOYEE
	    
	    @GetMapping("/employee/profile")
	    public ProfileResponse profile(Authentication auth) {
	        return employeeService.getProfile(auth.getName());
	    }

	    //Endpoint accessible only by MANAGER role.
	    
	    @GetMapping("/manager/employees")
	    public List<ProfileResponse> allEmployees() {
	        return employeeService.getAllEmployees();
	    }
}
