package com.dynatrace.internship.controllers.nbpcontrollers;


import com.dynatrace.internship.exceptions.IncorrectInstitutionException;
import com.dynatrace.internship.getters.averagerate.AverageExchangeRateGetter;
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
import java.util.HashMap;
import java.util.Map;

@RestController
public class ExtremeRatesController {

	private final static String RATES_URL = "extremes";
	private final Map<String, ExtremeExchangeRatesGetter> rateGetters;
	private final static int MAXIMUM_QUOTATIONS = 255;
	private final static int MINIMUM_QUOTATIONS = 1;

	public ExtremeRatesController() {
		rateGetters = new HashMap<>();
		rateGetters.put("nbp", new XmlNBPExtremeExchangeRatesGetter('A'));
	}

	@GetMapping(value = {RATES_URL + "/**", RATES_URL})
	public String generateError() {
		return "Specify correct institution, currency code, and decimal number of last quotations from range <" + MINIMUM_QUOTATIONS + ":" + MAXIMUM_QUOTATIONS +
				">  in format declared in README file";
	}

	@GetMapping(value = RATES_URL + "/{institution}/{code}/{quotations}")
	public String getExtremeRates(@PathVariable("institution") String institution,@PathVariable("code") String currencyCode, @PathVariable("quotations") String quotations) {
		try {
			CurrencyParser parser = new CurrencyParserImpl();
			Currency currency = parser.getCurrencyInstance(currencyCode);

			QuotationsParser quotationsParser = new QuotationsParserImpl(MINIMUM_QUOTATIONS, MAXIMUM_QUOTATIONS);
			int numberOfQuotations = quotationsParser.getNumberOfQuotations(quotations);

			ExtremeExchangeRatesGetter extremeExchangeRatesGetter = getExtremeExchangeRatesGetter(institution);
			double maxExchangeRate = extremeExchangeRatesGetter.getMaxExchangeRate(currency, numberOfQuotations);
			double minExchangeRate = extremeExchangeRatesGetter.getMinExchangeRate(currency, numberOfQuotations);
			return currency.getCurrencyCode() + "<br/>Last quotations: " + numberOfQuotations +  "<br/>Max average exchange rate: " + maxExchangeRate +
					"<br/>Min average exchange rate: " + minExchangeRate;
		}
		catch (Exception err) {
			return err.getMessage();
		}
	}

	private ExtremeExchangeRatesGetter getExtremeExchangeRatesGetter(String institution) {
		ExtremeExchangeRatesGetter rateGetter = rateGetters.get(institution);
		if (rateGetter == null) {
			throw new IncorrectInstitutionException("Specify correct institution.");
		}
		return rateGetter;
	}
}
