package com.dynatrace.internship.connectors;

import java.util.Currency;

public interface AverageRateGetter {
    double GetAverageExchangeRate(Currency currency, String date);
}
