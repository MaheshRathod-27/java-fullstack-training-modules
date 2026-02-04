package com.logicoy.employeeportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logicoy.employeeportal.model.Employee;


//Repository interface for accessing and managing Employee persistence data

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByUsername(String username);
	   boolean existsByUsername(String username);

}
