package com.logicoy.api.productsearch.exception;

//Custom exception for invalid search filters.
public class InvalidFilterException extends RuntimeException {

    public InvalidFilterException(String message) {
        super(message);
    }
}
