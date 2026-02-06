package com.logicoy.api.productsearch.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// Standard error response format for all APIs.

@Schema(description = "Error response")
public class ErrorResponse {

    private String errorCode;
    private String message;

    // Constructor used in exception handlers
    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

   
    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
