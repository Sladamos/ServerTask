package com.dynatrace.internship.getters.majordifferencerate;

import com.dynatrace.internship.structures.RateDifference;

import java.util.Currency;

public interface MajorDifferenceRateGetter {
    RateDifference getMajorDifference(Currency currency, int numberOfQuotations);
}
