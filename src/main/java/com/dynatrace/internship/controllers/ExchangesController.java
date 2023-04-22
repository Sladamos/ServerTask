package com.dynatrace.internship.controllers;


import com.dynatrace.internship.connectors.AverageRateGetter;
import com.dynatrace.internship.connectors.NBPAverageRateGetter;
import com.dynatrace.internship.exceptions.IncorrectCurrencyCodeException;
import com.dynatrace.internship.parsers.CurrencyParser;
import com.dynatrace.internship.parsers.CurrencyParserImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
public class ExchangesController {

    private final String EXCHANGES_URL = "/exchanges";
    private final char NBP_TABLE_ID = 'A';

    @GetMapping(value = {EXCHANGES_URL + "/**", EXCHANGES_URL})
    public String generateError() {
        return "Specify correct currency code and date";
    }

    @GetMapping(value = EXCHANGES_URL + "/{code}/{date}")
    public String getExchangeRate(@PathVariable("code") String currencyCode, @PathVariable("date") String date)
    {
        try {
            CurrencyParser parser = new CurrencyParserImpl();
            Currency currency = parser.getCurrencyInstance(currencyCode);
            AverageRateGetter rateGetter = new NBPAverageRateGetter(NBP_TABLE_ID);
            return String.valueOf(rateGetter.GetAverageExchangeRate(currency, date));
        }
        catch (IncorrectCurrencyCodeException err)
        {
            return err.getMessage();
        }

        //is date okay
        //go to connector
    }
}
