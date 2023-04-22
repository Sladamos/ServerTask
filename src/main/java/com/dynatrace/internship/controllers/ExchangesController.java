package com.dynatrace.internship.controllers;


import com.dynatrace.internship.connectors.AverageRateGetter;
import com.dynatrace.internship.connectors.NBPAverageRateGetter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
public class ExchangesController {

    private final String EXCHANGES_URL = "/exchanges";
    private final char NBP_TABLE_ID = 'A';

    @GetMapping(value = {EXCHANGES_URL + "/*", EXCHANGES_URL})
    public String generateError() {
        return "Specify correct currency code and date";
    }

    @GetMapping(value = EXCHANGES_URL + "/{code}/{date}")
    public double getExchangeRate(@PathVariable("code") String currencyCode, @PathVariable("date") String date)
    {
        try {
            var xd = Currency.getInstance(currencyCode);
        }
        catch (Exception err)
        {
            return 15;
        }
        //create connector class
        //is code okay
        //is date okay
        //go to connector
        AverageRateGetter rateGetter = new NBPAverageRateGetter(NBP_TABLE_ID);
        return rateGetter.GetAverageExchangeRate(currencyCode, date);
    }
}
