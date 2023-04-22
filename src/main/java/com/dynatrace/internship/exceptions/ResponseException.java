package com.dynatrace.internship.exceptions;

public class ResponseException extends RuntimeException {
    public ResponseException(int responseCode, String responseBody) {
        super("Failed to get the source. HttpResponseCode " + responseCode + ". " + responseBody);
    }
}
