package com.dynatrace.internship.connectors.minmaxgetters;

import java.net.URL;

public class XmlNBPMinMaxGetter extends NBPMinMaxGetter {
	public XmlNBPMinMaxGetter(char tableId) {
		super(tableId);
	}

	@Override
	protected String getFormatAttribute() {
		return null;
	}

	@Override
	protected double getMaxValueFromURL(URL url) {
		return 0;
	}

	@Override
	protected double getMinValueFromURL(URL url) {
		return 0;
	}
}
