package com.dynatrace.internship.exceptions;

public class IncorrectCurrencyCodeException extends RuntimeException {
    public IncorrectCurrencyCodeException(String message) {
        super(message);
    }
}
