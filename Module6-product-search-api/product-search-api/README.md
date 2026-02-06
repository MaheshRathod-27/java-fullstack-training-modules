# Product Search API

## Overview

This project implements a Product Search API using Spring Boot.  
The goal of this project is to demonstrate:

- API contract design
- Query parameter–based search
- Sorting and pagination
- Swagger/OpenAPI documentation
- Proper error handling

The implementation is contract-focused and uses mock data (no database).

---

## What is Used

- Java 17
- Spring Boot (Spring Web)
- Spring Validation
- Springdoc OpenAPI (Swagger UI)
- Maven

---

## What is Implemented

- Versioned API endpoint (`/api/v1/products`)
- Search using query parameters
- Sorting (`priceAsc`, `priceDesc`, `rating`)
- Pagination metadata in response
- Graceful handling of empty results
- Centralized exception handling
- Swagger UI for API documentation

---

## Prerequisites

- Java 17 installed
- Maven installed
