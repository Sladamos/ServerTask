package com.dynatrace.internship;

import com.dynatrace.internship.parsers.CurrencyParser;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyParserTests {

    @Test
    public void CorrectCode()
    {
        CurrencyParser parser = new CurrencyParserImpl();
        Currency instance = parser.getCurrencyInstance("GBP");
        assertSame(Currency.getInstance("GBP"), instance);
    }

    @Test
    public void IncorrectCode()
    {
        CurrencyParser parser = new CurrencyParserImpl();
        assertThrows(IncorrectCodeError, parser.getCurrencyInstance("Incorrect code"));
    }
}
