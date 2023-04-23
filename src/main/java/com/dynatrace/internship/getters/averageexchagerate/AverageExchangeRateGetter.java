package com.dynatrace.internship.getters.averageexchagerate;

import java.time.LocalDate;
import java.util.Currency;

public interface AverageExchangeRateGetter {
    double getAverageExchangeRate(Currency currency, LocalDate date);
}
