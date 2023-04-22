package com.dynatrace.internship.controllers;


import com.dynatrace.internship.connectors.averagerategetters.AverageRateGetter;
import com.dynatrace.internship.connectors.averagerategetters.XmlNBPAverageRateGetter;
import com.dynatrace.internship.parsers.CurrencyParser;
import com.dynatrace.internship.parsers.CurrencyParserImpl;
import com.dynatrace.internship.parsers.DateParser;
import com.dynatrace.internship.parsers.DateParserImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

@RestController
public class ExchangesController {

    private final static String EXCHANGES_URL = "nbp/exchanges";
    private final static char NBP_TABLE_ID = 'A';

    @GetMapping(value = {EXCHANGES_URL + "/**", EXCHANGES_URL})
    public String generateError() {
        return "Specify correct currency code and date";
    }

    @GetMapping(value = EXCHANGES_URL + "/{code}/{date}")
    public String getExchangeRate(@PathVariable("code") String currencyCode, @PathVariable("date") String date) {
        try {
            CurrencyParser parser = new CurrencyParserImpl();
            Currency currency = parser.getCurrencyInstance(currencyCode);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateParser dateParser = new DateParserImpl(formatter);
            LocalDate localDate = dateParser.getFormattedDate(date);

            AverageRateGetter rateGetter = new XmlNBPAverageRateGetter(NBP_TABLE_ID);

            return currency.getCurrencyCode() +  " " + localDate.toString() + " " + String.valueOf(rateGetter.getAverageExchangeRate(currency, localDate));
        }
        catch (Exception err) {
            return err.getMessage();
        }
    }
}
