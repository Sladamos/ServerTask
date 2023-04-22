package com.dynatrace.internship.connectors;

import lombok.AllArgsConstructor;

import java.util.Currency;

@AllArgsConstructor
public class NBPAverageRateGetter implements AverageRateGetter {
    private char tableId;
    private final String RATE_PATH = "http://api.nbp.pl/api/exchangerates/rates/";

    @Override
    public double GetAverageExchangeRate(Currency currency, String date) {
        String finalPath = createFinalPath(currency, date);
        //200 okay
        //select
        return 0;
    }

    private String createFinalPath(Currency currency, String date) {
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(RATE_PATH);
        pathBuilder.append('/');
        pathBuilder.append(tableId);
        pathBuilder.append('/');
        pathBuilder.append(currency.getCurrencyCode());
        pathBuilder.append('/');
        pathBuilder.append(date);
        return pathBuilder.toString();
    }
}
