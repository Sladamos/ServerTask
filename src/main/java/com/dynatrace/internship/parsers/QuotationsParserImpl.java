package com.dynatrace.internship.parsers;

import com.dynatrace.internship.exceptions.IncorrectQuotationsException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuotationsParserImpl implements QuotationsParser {

	private final int minimumQuotations;
	private final int maximumQuotations;

	@Override
	public int getNumberOfQuotations(String quotations) {
		try {
			int numberOfQuotations = Integer.parseInt(quotations);

			if (numberOfQuotations > maximumQuotations) {
				throw new IncorrectQuotationsException("Number of quotations can't be higher than " + maximumQuotations);
			}

			if (numberOfQuotations < minimumQuotations) {
				throw new IncorrectQuotationsException("Number of quotations can't be smaller than " + minimumQuotations);
			}

			return numberOfQuotations;
		}
		catch (Exception err) {
			throw new RuntimeException("Specify decimal number of last quotations from range <" + minimumQuotations + ":" + maximumQuotations + ">");
		}
	}
}
