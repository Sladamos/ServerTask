package com.dynatrace.internship.getters.extremerate;

import java.util.Currency;

public interface ExtremeExchangeRatesGetter {
	double getMinExchangeRate(Currency currency, int numberOfQuotations);
	double getMaxExchangeRate(Currency currency, int numberOfQuotations);
}
