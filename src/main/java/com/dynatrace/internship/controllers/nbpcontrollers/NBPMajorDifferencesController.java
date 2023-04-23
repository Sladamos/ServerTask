package com.dynatrace.internship.controllers.nbpcontrollers;

import com.dynatrace.internship.getters.majordifferencerate.MajorDifferenceRateGetter;
import com.dynatrace.internship.getters.majordifferencerate.JsonNBPMajorDifferenceRateGetter;
import com.dynatrace.internship.parsers.*;
import com.dynatrace.internship.structures.RateDifference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
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

            MajorDifferenceRateGetter differenceRateGetter = new JsonNBPMajorDifferenceRateGetter(NBP_TABLE_ID);
            RateDifference difference =  differenceRateGetter.getMajorDifference(currency, numberOfQuotations);

            return currency.getCurrencyCode() + "<br/>Major rate difference: " + difference.getDifferenceValue() +
                    "<br/>One of possible dates: " + difference.getDifferenceDate();
        }
        catch (Exception err) {
            return err.getMessage();
        }
    }
}
