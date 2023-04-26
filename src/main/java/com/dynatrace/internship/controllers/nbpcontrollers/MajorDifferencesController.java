package com.dynatrace.internship.controllers.nbpcontrollers;

import com.dynatrace.internship.exceptions.IncorrectInstitutionException;
import com.dynatrace.internship.getters.majordifferencerate.MajorDifferenceRateGetter;
import com.dynatrace.internship.getters.majordifferencerate.JsonNBPMajorDifferenceRateGetter;
import com.dynatrace.internship.parsers.*;
import com.dynatrace.internship.structures.RateDifference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MajorDifferencesController {
	private final static String DIFFERENCES_URL = "differences";
	private final static int MAXIMUM_QUOTATIONS = 255;
	private final static int MINIMUM_QUOTATIONS = 1;
	private final Map<String, MajorDifferenceRateGetter> rateGetters;

	public MajorDifferencesController() {
		rateGetters = new HashMap<>();
		rateGetters.put("nbp", new JsonNBPMajorDifferenceRateGetter('C'));
	}

	@GetMapping(value = {DIFFERENCES_URL + "/**", DIFFERENCES_URL})
	public String generateError() {
		return "Specify correct instance, currency code, and decimal number of last quotations from range <" + MINIMUM_QUOTATIONS + ":" + MAXIMUM_QUOTATIONS +
				">  in format declared in README file";
	}

	@GetMapping(value = DIFFERENCES_URL + "/{institution}/{code}/{quotations}")
	public String getMajorDifferenceRate(@PathVariable("institution") String institution, @PathVariable("code") String currencyCode, @PathVariable("quotations") String quotations) {
		try {
			CurrencyParser parser = new CurrencyParserImpl();
			Currency currency = parser.getCurrencyInstance(currencyCode);

			QuotationsParser quotationsParser = new QuotationsParserImpl(MINIMUM_QUOTATIONS, MAXIMUM_QUOTATIONS);
			int numberOfQuotations = quotationsParser.getNumberOfQuotations(quotations);

			MajorDifferenceRateGetter differenceRateGetter = getMajorDifferenceRateGetter(institution);
			RateDifference difference =  differenceRateGetter.getMajorRateDifference(currency, numberOfQuotations);

			return currency.getCurrencyCode() + "<br/>Major rate difference: " + difference.getDifferenceValue() +
					"<br/>One of possible dates: " + difference.getDifferenceDate();
		}
		catch (Exception err) {
			return err.getMessage();
		}
	}

	private MajorDifferenceRateGetter getMajorDifferenceRateGetter(String institution) {
		MajorDifferenceRateGetter rateGetter = rateGetters.get(institution);
		if (rateGetter == null) {
			throw new IncorrectInstitutionException("Specify correct institution.");
		}
		return rateGetter;
	}
}
