package com.dynatrace.internship.controllers.nbpcontrollers;

import com.dynatrace.internship.connectors.averagerategetters.AverageRateGetter;
import com.dynatrace.internship.connectors.averagerategetters.XmlNBPAverageRateGetter;
import com.dynatrace.internship.parsers.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

public class NBPMajorDifferencesController {
    private final static String DIFFERENCES_URL = "nbp/differences";
    private final static char NBP_TABLE_ID = 'C';
    private final static int MAXIMUM_QUOTATIONS = 255;
    private final static int MINIMUM_QUOTATIONS = 1;

    @GetMapping(value = {DIFFERENCES_URL + "/**", DIFFERENCES_URL})
    public String generateError() {
        return "Specify correct currency code, and decimal number of last quotations from range <" + MINIMUM_QUOTATIONS + ":" + MAXIMUM_QUOTATIONS + ">";
    }

    @GetMapping(value = DIFFERENCES_URL + "/{code}/{quotations}")
    public String getMajorDifference(@PathVariable("code") String currencyCode, @PathVariable("quotations") String quotations) {
        try {
            CurrencyParser parser = new CurrencyParserImpl();
            Currency currency = parser.getCurrencyInstance(currencyCode);


            QuotationsParser quotationsParser = new QuotationsParserImpl(MINIMUM_QUOTATIONS, MAXIMUM_QUOTATIONS);
            int numberOfQuotations = quotationsParser.getNumberOfQuotations(quotations);


            //TODO
            //implement MajorDifferenceGetter
            return "oh";
        }
        catch (Exception err) {
            return err.getMessage();
        }
    }
}
