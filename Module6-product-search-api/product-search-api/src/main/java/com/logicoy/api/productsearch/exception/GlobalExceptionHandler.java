package com.logicoy.api.productsearch.exception;

import com.logicoy.api.productsearch.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

//Handles all API exceptions in a centralized way.
 
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles business validation errors
    @ExceptionHandler(InvalidFilterException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFilter(InvalidFilterException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("INVALID_FILTER", ex.getMessage()));
    }

    // Handles query parameter type mismatch errors
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {

        String paramName = ex.getName();

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                        "INVALID_QUERY_PARAM",
                        "Invalid value for query parameter: " + paramName
                ));
    }
}
