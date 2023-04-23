package com.dynatrace.internship.getters.majordifferencerate;

import com.dynatrace.internship.structures.RateDifference;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.net.URL;
import java.time.LocalDate;

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
        try {
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xPath = xpf.newXPath();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(url.openStream());
            //get nodes from document
            //for each -> select node with biggest difference
            //return RateDifference
            LocalDate differenceDate;
            double differenceValue;
            return new RateDifference(differenceDate, differenceValue);
            return Double.parseDouble(value);
        }
        catch (Exception err) {
            throw new RuntimeException(err);
        }
    }
}
