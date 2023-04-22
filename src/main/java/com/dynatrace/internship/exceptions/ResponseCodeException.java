package com.dynatrace.internship.exceptions;

public class ResponseCodeException extends RuntimeException {
    public ResponseCodeException(int responseCode, String errorSource) {
        super("Failed to get the source. HttpResponseCode " + responseCode + ". " + errorSource);
    }
}
