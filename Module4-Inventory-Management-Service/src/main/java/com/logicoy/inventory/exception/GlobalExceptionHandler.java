package com.logicoy.inventory.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Handles exceptions globally for all REST controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Logger for tracking errors and warnings
    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handles product not found scenarios
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {

        log.warn("Client error: {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // Handles insufficient stock scenarios
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> handleInsufficientStock(InsufficientStockException ex) {

        log.warn("Client error: {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    // Handles any unexpected server errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedError(Exception ex) {

        log.error("Unexpected server error occurred", ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error");
    }
}
