package com.dynatrace.internship.getters.extremerate;

import com.dynatrace.internship.creators.HttpConnectionCreator;
import com.dynatrace.internship.exceptions.ConnectionException;
import com.dynatrace.internship.exceptions.ExtremeExchangeRateException;
import com.dynatrace.internship.exceptions.ResponseException;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

@AllArgsConstructor
public abstract class NBPExtremeExchangeRatesGetter implements ExtremeExchangeRatesGetter {
	private char tableId;
	private final String RATE_PATH = "http://api.nbp.pl/api/exchangerates/rates/";

	@Override
	public double getMinExchangeRate(Currency currency, int numberOfQuotations) {
		String finalPath = createFinalPath(currency, numberOfQuotations);
		try {
			var creator = new HttpConnectionCreator();
			HttpURLConnection conn = creator.createConnection(finalPath, "GET");
			tryToConnect(conn);
			return getMinExchangeRateFromURL(conn.getURL());

		} catch (Exception err) {
			throw new ExtremeExchangeRateException(err.getMessage());
		}
	}

	@Override
	public double getMaxExchangeRate(Currency currency, int numberOfQuotations) {
		String finalPath = createFinalPath(currency, numberOfQuotations);
		try {
			var creator = new HttpConnectionCreator();
			HttpURLConnection conn = creator.createConnection(finalPath, "GET");
			tryToConnect(conn);
			return getMaxExchangeRateFromURL(conn.getURL());

		} catch (Exception err) {
			throw new ExtremeExchangeRateException(err.getMessage());
		}
	}

	protected abstract String getFormatAttribute();

	protected abstract double getMaxExchangeRateFromURL(URL url);

	protected abstract double getMinExchangeRateFromURL(URL url);

	private String createFinalPath(Currency currency, int numberOfQuotations) {
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(RATE_PATH);
		pathBuilder.append(tableId);
		pathBuilder.append('/');
		pathBuilder.append(currency.getCurrencyCode());
		pathBuilder.append("/last/");
		pathBuilder.append(numberOfQuotations);
		pathBuilder.append(getFormatAttribute());
		return pathBuilder.toString();
	}

	private void tryToConnect(HttpURLConnection conn) {
		try {
			conn.connect();
			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				String responseMessage = conn.getResponseMessage();
				throw new ResponseException(responseCode, responseMessage);
			}
		}
		catch (IOException err) {
			throw new ConnectionException(err.getMessage());
		}
	}
}
