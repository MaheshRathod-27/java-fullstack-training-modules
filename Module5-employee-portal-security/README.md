Employee Portal Security – Assignment Overview

This project implements security for an Employee Portal REST API using Spring Boot.
It demonstrates authentication, authorization, and JWT-based stateless security.

The system follows industry best practices for securing REST APIs without using HTTP sessions.

Features

The application allows users to:

Register with a role (EMPLOYEE or MANAGER)

Log in using username and password

Receive a JWT token on successful authentication

Access APIs based on assigned roles using the JWT

Security Design

Stateless authentication using JWT

No HTTP sessions are used

Each request is authenticated via the Authorization header

Role-based access control:

EMPLOYEE → access own profile

MANAGER → access all employees

Passwords are securely stored using BCrypt

DTOs are used to avoid exposing database entities

How It Works (High Level):-

User registers with username, password, and role

User logs in with credentials

Application authenticates credentials using Spring Security

A JWT token is generated and returned

Client sends the JWT with every protected request

JWT is validated by a security filter on each request

Prerequisites->

To run this project, ensure you have:-

Java

Maven

MySQL running locally

Create the database manually:

CREATE DATABASE employee_portal;


Note: Spring Boot does not create the database automatically.

JWT Secret Key Requirement

The JWT secret key is not hardcoded

It must be provided as an environment variable

The secret is used to sign and validate JWT tokens

Example
JWT_SECRET=your_own_secure_secret_key


⚠️ Important:
The application will not start or function correctly if the JWT secret key is missing.