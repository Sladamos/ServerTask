package com.dynatrace.internship.getters.majordifferencerate;

import com.dynatrace.internship.structures.RateDifference;

import java.net.URL;

public class XmlNBPMajorDifferenceRateGetter extends NBPMajorDifferenceRateGetter {
    public XmlNBPMajorDifferenceRateGetter(char tableId) {
        super(tableId);
    }

    @Override
    protected String getFormatAttribute() {
        return "?format=xml";
    }

    @Override
    protected RateDifference getMajorDifferenceFromUrl(URL url) {
        return null;
    }
}
