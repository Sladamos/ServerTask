package com.dynatrace.internship.connectors.minmaxgetters;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.net.URL;

public class XmlNBPMinMaxGetter extends NBPMinMaxGetter {
	public XmlNBPMinMaxGetter(char tableId) {
		super(tableId);
	}

	@Override
	protected String getFormatAttribute() {
		return "?format=xml";
	}

	@Override
	protected double getMaxValueFromURL(URL url) {
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
	protected double getMinValueFromURL(URL url) {
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
