package com.dynatrace.internship.connectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NBPAverageRateGetter implements AverageRateGetter {
    private char tableId;
    private final String RATE_PATH = "http://api.nbp.pl/api/exchangerates/rates/";

    @Override
    public double GetAverageExchangeRate(String code, String date) {
        String finalPath = RATE_PATH + tableId + code + date;
        //200 okay
        //select
        return 0;
    }
}
