package com.dynatrace.internship.connectors;

import java.time.LocalDate;
import java.util.Currency;

public interface AverageRateGetter {
    double GetAverageExchangeRate(Currency currency, LocalDate date);
}
