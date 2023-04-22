package com.dynatrace.internship.connectors.averagerategeggets;

import com.dynatrace.internship.creators.ConnectionCreator;
import com.dynatrace.internship.creators.HttpConnectionCreator;
import com.dynatrace.internship.exceptions.ResponseCodeException;
import lombok.AllArgsConstructor;

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
            //try to connect
            conn.connect();

            int responseCode = conn.getResponseCode();
            if(responseCode != 200) {
                throw new ResponseCodeException(responseCode); //include body from url?
            }
            //get value from input

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
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
