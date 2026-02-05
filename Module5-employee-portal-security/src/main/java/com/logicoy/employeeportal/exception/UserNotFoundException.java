package com.logicoy.employeeportal.exception;

// Thrown when a requested user does not exist in the database
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}