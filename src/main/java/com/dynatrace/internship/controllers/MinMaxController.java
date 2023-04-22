package com.dynatrace.internship.controllers;


import com.dynatrace.internship.connectors.minmaxgetters.XmlNBPMinMaxGetter;
import com.dynatrace.internship.connectors.minmaxgetters.MinMaxGetter;
import com.dynatrace.internship.parsers.CurrencyParser;
import com.dynatrace.internship.parsers.CurrencyParserImpl;
import com.dynatrace.internship.parsers.QuotationsParser;
import com.dynatrace.internship.parsers.QuotationsParserImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
public class MinMaxController {

	private final static String MINMAX_URL = "nbp/minmax";
	private final static char NBP_TABLE_ID = 'A';
	private final static int MAXIMUM_QUOTATIONS = 255;
	private final static int MINIMUM_QUOTATIONS = 1;

	@GetMapping(value = {MINMAX_URL + "/**", MINMAX_URL})
	public String generateError() {
		return "Specify correct currency code, and decimal number of last quotations from range <" + MINIMUM_QUOTATIONS + ":" + MAXIMUM_QUOTATIONS + ">";
	}

	@GetMapping(value = MINMAX_URL + "/{code}/{quotations}")
	public String getExchangeRate(@PathVariable("code") String currencyCode, @PathVariable("quotations") String quotations) {
		try {
			CurrencyParser parser = new CurrencyParserImpl();
			Currency currency = parser.getCurrencyInstance(currencyCode);

			QuotationsParser quotationsParser = new QuotationsParserImpl(MINIMUM_QUOTATIONS, MAXIMUM_QUOTATIONS);

			MinMaxGetter minMaxGetter = new XmlNBPMinMaxGetter(NBP_TABLE_ID);
			//TODO specify output
			//XMLGetter
			return "blabla";
		}
		catch (Exception err) {
			return err.getMessage();
		}
	}
}
