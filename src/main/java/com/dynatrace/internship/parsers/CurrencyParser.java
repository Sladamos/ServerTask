package com.dynatrace.internship.parsers;

import java.util.Currency;

public interface CurrencyParser {
    Currency getCurrencyInstance(String currencyCode);
}
