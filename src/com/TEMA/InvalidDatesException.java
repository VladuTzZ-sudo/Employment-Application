package com.TEMA;

public class InvalidDatesException extends RuntimeException {
    public InvalidDatesException(String errorMessage) {
        super(errorMessage);
    }
}