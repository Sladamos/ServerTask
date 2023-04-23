package com.dynatrace.internship.getters.majordifferencerate;

import com.dynatrace.internship.exceptions.MajorDifferenceRateException;
import com.dynatrace.internship.parsers.DateParser;
import com.dynatrace.internship.parsers.DateParserImpl;
import com.dynatrace.internship.structures.RateDifference;
import org.apache.commons.io.IOUtils;
import org.json.*;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonNBPMajorDifferenceRateGetter extends NBPMajorDifferenceRateGetter {
    public JsonNBPMajorDifferenceRateGetter(char tableId) {
        super(tableId);
    }

    @Override
    protected String getFormatAttribute() {
        return "?format=json";
    }

    @Override
    protected RateDifference getMajorDifferenceFromUrl(URL url) {
        try {
            JSONObject json = createJsonFromURL(url);
            JSONArray rates = (JSONArray) json.get("rates");
            if(rates.length() == 0)
                throw new MajorDifferenceRateException("No results found!");
            return getMajorDifferenceFromRates(rates);
        }
        catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    private JSONObject createJsonFromURL(URL url) {
        try {
            String json = IOUtils.toString(url, StandardCharsets.UTF_8);
            return new JSONObject(json);
        }
        catch (Exception err) {
            throw new JSONException("Incorrect URL");
        }
    }

    private RateDifference getMajorDifferenceFromRates(JSONArray rates) {
        JSONObject majorRate = getMajorRate(rates);
        BigDecimal majorSellValue = (BigDecimal)majorRate.get("ask");
        BigDecimal majorBuyValue = (BigDecimal)majorRate.get("bid");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateParser parser = new DateParserImpl(formatter);

        LocalDate differenceDate = parser.getFormattedDate((String)majorRate.get("effectiveDate"));
        double differenceValue = majorSellValue.subtract(majorBuyValue).abs().doubleValue();
        return new RateDifference(differenceDate, differenceValue);
    }

    private JSONObject getMajorRate(JSONArray rates) {
        JSONObject majorRate = (JSONObject) rates.get(0);
        for(int i = 0; i < rates.length(); i++) {
            JSONObject rate = rates.getJSONObject(i);
            BigDecimal majorSellValue = (BigDecimal)majorRate.get("ask");
            BigDecimal majorBuyValue = (BigDecimal)majorRate.get("bid");
            BigDecimal rateSellValue = (BigDecimal)rate.get("ask");
            BigDecimal rateBuyValue = (BigDecimal)rate.get("bid");
            BigDecimal majorDifference = majorSellValue.subtract(majorBuyValue).abs();
            BigDecimal rateDifference = rateSellValue.subtract(rateBuyValue).abs();
            if(majorDifference.compareTo(rateDifference) < 0) {
                majorRate = rate;
            }
        }
        return majorRate;
    }
}
