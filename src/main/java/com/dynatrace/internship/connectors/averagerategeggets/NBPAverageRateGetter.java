package com.dynatrace.internship.connectors.averagerategeggets;

import com.dynatrace.internship.creators.HttpConnectionCreator;
import com.dynatrace.internship.exceptions.AverageExchangeRateException;
import com.dynatrace.internship.exceptions.ConnectionException;
import com.dynatrace.internship.exceptions.ResponseException;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Currency;

@AllArgsConstructor
public abstract class NBPAverageRateGetter implements AverageRateGetter {
    private char tableId;
    private final String RATE_PATH = "http://api.nbp.pl/api/exchangerates/rates/";

    @Override
    public double getAverageExchangeRate(Currency currency, LocalDate date) {
        String finalPath = createFinalPath(currency, date);
        try {
            var creator = new HttpConnectionCreator();
            HttpURLConnection conn = creator.createConnection(finalPath, "GET");
            tryToConnect(conn);
            return getValueFromURL(conn.getURL());

        } catch (Exception err) {
            throw new AverageExchangeRateException(err.getMessage());
        }
    }

    private void tryToConnect(HttpURLConnection conn) {
        try {
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                String responseMessage = conn.getResponseMessage();
                throw new ResponseException(responseCode, responseMessage);
            }
        }
        catch (IOException err) {
            throw new ConnectionException(err.getMessage());
        }
    }

    private String createFinalPath(Currency currency, LocalDate date) {
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(RATE_PATH);
        pathBuilder.append(tableId);
        pathBuilder.append('/');
        pathBuilder.append(currency.getCurrencyCode());
        pathBuilder.append('/');
        pathBuilder.append(date);
        pathBuilder.append(getFormatAttribute());
        return pathBuilder.toString();
    }

    protected abstract String getFormatAttribute();

    protected abstract double getValueFromURL(URL url);
}
