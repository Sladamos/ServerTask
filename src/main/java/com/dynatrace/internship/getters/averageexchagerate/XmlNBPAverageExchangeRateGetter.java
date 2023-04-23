package com.dynatrace.internship.getters.averageexchagerate;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.net.URL;

public class XmlNBPAverageExchangeRateGetter extends NBPAverageExchangeRateGetter {
    public XmlNBPAverageExchangeRateGetter(char tableId) {
        super(tableId);
    }

    @Override
    protected String getFormatAttribute() {
        return "?format=xml";
    }

    @Override
    protected double getValueFromURL(URL url) {
        try {
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xPath = xpf.newXPath();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(url.openStream());
            String value = xPath.evaluate("//Mid[1]/text()", document.getDocumentElement());
            return Double.parseDouble(value);
        }
        catch(Exception err) {
            throw new RuntimeException(err);
        }
    }
}
