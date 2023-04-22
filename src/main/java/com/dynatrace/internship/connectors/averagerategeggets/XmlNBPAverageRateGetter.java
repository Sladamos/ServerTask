package com.dynatrace.internship.connectors.averagerategeggets;

import java.net.URL;

public class XmlNBPAverageRateGetter extends NBPAverageRateGetter{
    public XmlNBPAverageRateGetter(char tableId) {
        super(tableId);
    }

    @Override
    protected String getFormatAttribute() {
        return "?format=xml";
    }

    @Override
    protected double getValueFromURL(URL url) {
        return 0;
    }
}
