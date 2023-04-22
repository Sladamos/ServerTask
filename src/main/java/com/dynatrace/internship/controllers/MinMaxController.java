package com.dynatrace.internship.controllers;


import com.dynatrace.internship.connectors.minmaxgetters.XmlNBPMinMaxGetter;
import com.dynatrace.internship.connectors.minmaxgetters.MinMaxGetter;
import com.dynatrace.internship.exceptions.IncorrectQuotationsException;
import com.dynatrace.internship.parsers.CurrencyParser;
import com.dynatrace.internship.parsers.CurrencyParserImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
public class MinMaxController {

	private final String MINMAX_URL = "nbp/minmax";
	private final char NBP_TABLE_ID = 'A';
	private final int MAXIMUM_QUOTATIONS = 255;
	private final int MINIMUM_QUOTATIONS = 1;

	@GetMapping(value = {MINMAX_URL + "/**", MINMAX_URL})
	public String generateError() {
		return "Specify correct currency code, and decimal number of last quotations from range <" + MINIMUM_QUOTATIONS + ":" + MAXIMUM_QUOTATIONS + ">";
	}

	@GetMapping(value = MINMAX_URL + "/{code}/{quotations}")
	public String getExchangeRate(@PathVariable("code") String currencyCode, @PathVariable("quotations") String quotations) {
		try {
			CurrencyParser parser = new CurrencyParserImpl();
			Currency currency = parser.getCurrencyInstance(currencyCode);

			int numberOfQuotations = Integer.parseInt(quotations);
			if(numberOfQuotations > MAXIMUM_QUOTATIONS) {
				throw new IncorrectQuotationsException("Number of quotations can't be higher than " + MAXIMUM_QUOTATIONS);
			}

			if(numberOfQuotations < MINIMUM_QUOTATIONS) {
				throw new IncorrectQuotationsException("Number of quotations can't be smaller than " + MINIMUM_QUOTATIONS);
			}

			MinMaxGetter minMaxGetter = new XmlNBPMinMaxGetter(NBP_TABLE_ID);
			//TODO specify output
			//QuotationsParser
			//XML
			return "blabla";
		}
		catch (NumberFormatException err)
		{
			return "Specify decimal number of last quotations from range <1;255>";
		}
		catch (Exception err) {
			return err.getMessage();
		}
	}
}
