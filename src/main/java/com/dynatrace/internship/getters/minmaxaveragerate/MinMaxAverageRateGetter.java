package com.dynatrace.internship.getters.minmaxaveragerate;

import java.util.Currency;

public interface MinMaxAverageRateGetter {
	double getMinAverageValue(Currency currency, int numberOfQuotations);
	double getMaxAverageValue(Currency currency, int numberOfQuotations);
}
