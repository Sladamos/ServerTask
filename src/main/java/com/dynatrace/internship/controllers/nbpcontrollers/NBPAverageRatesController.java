package com.dynatrace.internship.controllers.nbpcontrollers;


import com.dynatrace.internship.getters.averagerate.AverageExchangeRateGetter;
import com.dynatrace.internship.getters.averagerate.XmlNBPAverageExchangeRateGetter;
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
public class NBPAverageRatesController {

    private final static String RATES_URL = "nbp/exchanges";
    private final static char NBP_TABLE_ID = 'A';

    @GetMapping(value = {RATES_URL + "/**", RATES_URL})
    public String generateError() {
        return "Specify correct currency code and date in format declared in README file";
    }

    @GetMapping(value = RATES_URL + "/{code}/{date}")
    public String getExchangeRate(@PathVariable("code") String currencyCode, @PathVariable("date") String date) {
        try {
            CurrencyParser parser = new CurrencyParserImpl();
            Currency currency = parser.getCurrencyInstance(currencyCode);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateParser dateParser = new DateParserImpl(formatter);
            LocalDate localDate = dateParser.getFormattedDate(date);

            AverageExchangeRateGetter rateGetter = new XmlNBPAverageExchangeRateGetter(NBP_TABLE_ID);

            return currency.getCurrencyCode() +  "<br/>Selected date: " + localDate.toString() + "<br/>Average rate: " + rateGetter.getAverageExchangeRate(currency, localDate);
        }
        catch (Exception err) {
            return err.getMessage();
        }
    }
}