🔐 Employee Portal Security (Spring Boot)
📌 Overview

Employee Portal Security is a Spring Boot REST API that demonstrates JWT-based authentication and role-based authorization using stateless security (no HTTP sessions).
It follows enterprise-level security best practices for backend applications.

🚀 Features
-User registration with roles (EMPLOYEE / MANAGER)
-Login using username & password
-JWT generation on successful authentication
-Role-based API access
-Stateless authentication using JWT
-Secure password hashing with BCrypt

🔒 Security Design

-JWT-based stateless authentication
-Authorization via Authorization: Bearer <token> header

Role-Based Access Control (RBAC):-

-EMPLOYEE → Access own profile
-MANAGER → Access all employees

DTO usage to avoid exposing entities

🔗 API Endpoints
🔐 Authentication

POST /auth/register → Register user with role
POST /auth/login → Authenticate & receive JWT

👤 Employee Access

GET /employees/profile → View own profile (EMPLOYEE)
GET /employees → View all employees (MANAGER)

JWT Required:
Authorization: Bearer <JWT_TOKEN>

🔁 Authentication Flow
-User registers with credentials & role
-User logs in with valid credentials
-Spring Security authenticates user
-JWT is generated and returned
-JWT is validated on every protected request

🧰 Tech Stack
-Java 17
-Spring Boot
-Spring Security
-JWT
-MySQL
-Maven

✅ Prerequisites
-Java 17+
-Maven
-MySQL (local)
-Database
(CREATE DATABASE employee_portal;)
⚠️ Spring Boot does not create the database automatically.

🔑 JWT Secret Configuration

-JWT secret is not hardcoded
-Must be set as an environment variable
-JWT_SECRET=your_secure_secret_key(need in your system environment)
⚠️ Application will not start without this variable.

📁 Project Highlights
-Clean layered architecture
-Custom JWT filter
-Role-based authorization
-Production-style security configuration
