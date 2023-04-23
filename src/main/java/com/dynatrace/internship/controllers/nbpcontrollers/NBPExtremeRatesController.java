package com.dynatrace.internship.controllers.nbpcontrollers;


import com.dynatrace.internship.getters.extremerate.XmlNBPExtremeExchangeRatesGetter;
import com.dynatrace.internship.getters.extremerate.ExtremeExchangeRatesGetter;
import com.dynatrace.internship.parsers.CurrencyParser;
import com.dynatrace.internship.parsers.CurrencyParserImpl;
import com.dynatrace.internship.parsers.QuotationsParser;
import com.dynatrace.internship.parsers.QuotationsParserImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
public class NBPExtremeRatesController {

	private final static String RATES_URL = "nbp/extremes";
	private final static char NBP_TABLE_ID = 'A';
	private final static int MAXIMUM_QUOTATIONS = 255;
	private final static int MINIMUM_QUOTATIONS = 1;

	@GetMapping(value = {RATES_URL + "/**", RATES_URL})
	public String generateError() {
		return "Specify correct currency code, and decimal number of last quotations from range <" + MINIMUM_QUOTATIONS + ":" + MAXIMUM_QUOTATIONS +
				">  in format declared in README file";
	}

	@GetMapping(value = RATES_URL + "/{code}/{quotations}")
	public String getExtremeRates(@PathVariable("code") String currencyCode, @PathVariable("quotations") String quotations) {
		try {
			CurrencyParser parser = new CurrencyParserImpl();
			Currency currency = parser.getCurrencyInstance(currencyCode);

			QuotationsParser quotationsParser = new QuotationsParserImpl(MINIMUM_QUOTATIONS, MAXIMUM_QUOTATIONS);
			int numberOfQuotations = quotationsParser.getNumberOfQuotations(quotations);

			ExtremeExchangeRatesGetter extremeExchangeRatesGetter = new XmlNBPExtremeExchangeRatesGetter(NBP_TABLE_ID);
			double maxExchangeRate = extremeExchangeRatesGetter.getMaxExchangeRate(currency, numberOfQuotations);
			double minExchangeRate = extremeExchangeRatesGetter.getMinExchangeRate(currency, numberOfQuotations);
			return currency.getCurrencyCode() + "<br/>Last quotations: " + numberOfQuotations +  "<br/>Max average exchange rate: " + maxExchangeRate +
					"<br/>Min average exchange rate: " + minExchangeRate;
		}
		catch (Exception err) {
			return err.getMessage();
		}
	}
}
