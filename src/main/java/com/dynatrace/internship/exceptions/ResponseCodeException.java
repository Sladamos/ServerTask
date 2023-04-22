package com.dynatrace.internship.exceptions;

public class ResponseCodeException extends RuntimeException {
    public ResponseCodeException(int responseCode) {
        super("HttpResponseCode" + responseCode);
    }
}
