package com.dynatrace.internship.creators;

public interface ConnectionCreator<T> {
    T createConnection(String path, String method);
}
