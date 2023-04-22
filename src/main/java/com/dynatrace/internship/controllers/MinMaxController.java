package com.dynatrace.internship.controllers;

import com.dynatrace.internship.connectors.averagerategetters.AverageRateGetter;
import com.dynatrace.internship.connectors.averagerategetters.XmlNBPAverageRateGetter;
import com.dynatrace.internship.parsers.CurrencyParser;
import com.dynatrace.internship.parsers.CurrencyParserImpl;
import com.dynatrace.internship.parsers.DateParser;
import com.dynatrace.internship.parsers.DateParserImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

@RestController
public class MinMaxController {

	private final String MINMAX_URL = "nbp/minmax";
	private final char NBP_TABLE_ID = 'A';

	@GetMapping(value = {MINMAX_URL + "/**", MINMAX_URL})
	public String generateError() {
		return "Specify correct currency code, and decimal number of last equations from range <1;255>";
	}

	@GetMapping(value = MINMAX_URL + "/{code}/{equations}")
	public String getExchangeRate(@PathVariable("code") String currencyCode, @PathVariable("equations") String equations) {
		try {
			CurrencyParser parser = new CurrencyParserImpl();
			Currency currency = parser.getCurrencyInstance(currencyCode);

			int numberOfEquations = Integer.parseInt(equations);

			//AverageRateGetter rateGetter = new XmlNBPAverageRateGetter(NBP_TABLE_ID);
			//return currency.getCurrencyCode() +  " " + localDate.toString() + " " + String.valueOf(rateGetter.getAverageExchangeRate(currency, localDate));
			return "blabla";
		}
		catch (NumberFormatException err)
		{
			return "Specify decimal number of last equations from range <1;255>";
		}
		catch (Exception err) {
			return err.getMessage();
		}
	}
}
