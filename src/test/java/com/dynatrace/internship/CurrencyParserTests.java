package com.dynatrace.internship;

import com.dynatrace.internship.exceptions.IncorrectCurrencyCodeException;
import com.dynatrace.internship.parsers.CurrencyParser;
import com.dynatrace.internship.parsers.CurrencyParserImpl;
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
        assertThrows(IncorrectCurrencyCodeException.class, () -> parser.getCurrencyInstance("Incorrect code"), "Specify currency code compatible with ISO 4217");
    }
}
