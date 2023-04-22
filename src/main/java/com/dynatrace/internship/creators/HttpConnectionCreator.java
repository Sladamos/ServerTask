package com.dynatrace.internship.creators;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionCreator implements ConnectionCreator<HttpURLConnection> {

    @Override
    public HttpURLConnection createConnection(String path, String method) {
        try {
            URL url = new URL(path);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            return conn;
        }
        catch (Exception err) {
            throw new RuntimeException(err);
        }
    }
}
