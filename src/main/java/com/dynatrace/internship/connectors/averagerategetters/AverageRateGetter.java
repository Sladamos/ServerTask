package com.dynatrace.internship.connectors.averagerategetters;

import java.time.LocalDate;
import java.util.Currency;

public interface AverageRateGetter {
    double getAverageExchangeRate(Currency currency, LocalDate date);
}
