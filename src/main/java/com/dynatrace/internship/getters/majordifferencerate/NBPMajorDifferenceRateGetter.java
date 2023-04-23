package com.dynatrace.internship.getters.majordifferencerate;

import com.dynatrace.internship.creators.HttpConnectionCreator;
import com.dynatrace.internship.exceptions.ConnectionException;
import com.dynatrace.internship.exceptions.MajorDifferenceRateException;
import com.dynatrace.internship.exceptions.ResponseException;
import com.dynatrace.internship.structures.RateDifference;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

@AllArgsConstructor
public abstract class NBPMajorDifferenceRateGetter implements MajorDifferenceRateGetter{
    private char tableId;
    private final String RATE_PATH = "http://api.nbp.pl/api/exchangerates/rates/";

    @Override
    public RateDifference getMajorRateDifference(Currency currency, int numberOfQuotations) {
        String finalPath = createFinalPath(currency, numberOfQuotations);
        try {
            var creator = new HttpConnectionCreator();
            HttpURLConnection conn = creator.createConnection(finalPath, "GET");
            tryToConnect(conn);
            return getMajorRateDifferenceFromUrl(conn.getURL());

        } catch (Exception err) {
            throw new MajorDifferenceRateException(err.getMessage());
        }
    }

    private String createFinalPath(Currency currency, int numberOfQuotations) {
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(RATE_PATH);
        pathBuilder.append(tableId);
        pathBuilder.append('/');
        pathBuilder.append(currency.getCurrencyCode());
        pathBuilder.append("/last/");
        pathBuilder.append(numberOfQuotations);
        pathBuilder.append(getFormatAttribute());
        return pathBuilder.toString();
    }

    protected abstract String getFormatAttribute();

    protected abstract RateDifference getMajorRateDifferenceFromUrl(URL url);

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
}
