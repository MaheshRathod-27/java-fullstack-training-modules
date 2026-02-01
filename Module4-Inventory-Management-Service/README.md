# Assignment 4 – Inventory Management Service

## Overview
This assignment focuses on implementing **logging and global exception handling** in a Spring Boot REST application.  
The service simulates inventory operations to demonstrate **observability and error handling best practices**.

---

## Purpose
- To understand how exceptions propagate in Spring Boot
- To apply centralized exception handling
- To use appropriate logging levels
- To configure Logback for different environments

---

## Specific Features
- Custom domain exceptions for business errors
- Global exception handling using `@RestControllerAdvice`
- Structured logging with SLF4J
- Logback configuration with `dev` and `prod` profiles
- Async logging configuration for production

---

## Prerequisites
- Java 17 or above
- Maven
- Basic knowledge of Spring Boot and REST APIs
- Familiarity with logging concepts

