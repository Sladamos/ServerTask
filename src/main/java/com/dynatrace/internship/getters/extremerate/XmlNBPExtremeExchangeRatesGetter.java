package com.dynatrace.internship.getters.extremerate;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.net.URL;

public class XmlNBPExtremeExchangeRatesGetter extends NBPExtremeExchangeRatesGetter {
	public XmlNBPExtremeExchangeRatesGetter(char tableId) {
		super(tableId);
	}

	@Override
	protected String getFormatAttribute() {
		return "?format=xml";
	}

	@Override
	protected double getMaxExchangeRateFromURL(URL url) {
		try {
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xPath = xpf.newXPath();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(url.openStream());
			String value = xPath.evaluate("//Rate[not(../Rate/Mid > Mid)]/Mid/text()", document.getDocumentElement());
			return Double.parseDouble(value);
		}
		catch (Exception err) {
			throw new RuntimeException(err);
		}
	}

	@Override
	protected double getMinExchangeRateFromURL(URL url) {
		try {
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xPath = xpf.newXPath();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(url.openStream());
			String value = xPath.evaluate("//Rate[not(../Rate/Mid < Mid)]/Mid/text()", document.getDocumentElement());
			return Double.parseDouble(value);
		}
		catch (Exception err) {
			throw new RuntimeException(err);
		}
	}
}
