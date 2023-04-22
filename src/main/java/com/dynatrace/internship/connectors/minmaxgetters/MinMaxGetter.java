package com.dynatrace.internship.connectors.minmaxgetters;

import java.util.Currency;

public interface MinMaxGetter {
	double getMinAverageValue(Currency currency, int numberOfQuotations);
	double getMaxAverageValue(Currency currency, int numberOfQuotations);
}
