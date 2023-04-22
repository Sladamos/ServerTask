package com.dynatrace.internship.parsers;

import com.dynatrace.internship.exceptions.IncorrectCurrencyCodeException;

import java.util.Currency;

public class CurrencyParserImpl implements CurrencyParser {
    @Override
    public Currency getCurrencyInstance(String currencyCode) {
        try {
            return Currency.getInstance(currencyCode);
        }
        catch (Exception err) {
            throw new IncorrectCurrencyCodeException("Specify currency code compatible with ISO 4217");
        }
    }
}
