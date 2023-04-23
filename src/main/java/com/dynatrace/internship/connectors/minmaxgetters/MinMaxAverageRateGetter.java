package com.dynatrace.internship.connectors.minmaxgetters;

import java.util.Currency;

public interface MinMaxAverageRateGetter {
	double getMinAverageValue(Currency currency, int numberOfQuotations);
	double getMaxAverageValue(Currency currency, int numberOfQuotations);
}
