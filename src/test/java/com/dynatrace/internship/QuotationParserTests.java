package com.dynatrace.internship;

import com.dynatrace.internship.exceptions.IncorrectQuotationsException;
import com.dynatrace.internship.parsers.QuotationsParser;
import com.dynatrace.internship.parsers.QuotationsParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QuotationParserTests {

	private QuotationsParser parser;

	@BeforeEach
	public void initializeParser()
	{
		parser = new QuotationsParserImpl(1, 255);
	}

	@Test
	public void correctQuotations()
	{
		assertEquals(123, parser.getNumberOfQuotations("123"));
		assertEquals(255, parser.getNumberOfQuotations("255"));
		assertEquals(1, parser.getNumberOfQuotations("1"));
	}

	@Test
	public void smallerQuotation()
	{
		assertThrows(IncorrectQuotationsException.class, () -> parser.getNumberOfQuotations("0"));
		assertThrows(IncorrectQuotationsException.class, () -> parser.getNumberOfQuotations("-3"));
	}

	@Test
	public void biggerQuotation()
	{
		assertThrows(IncorrectQuotationsException.class, () -> parser.getNumberOfQuotations("300"));
		assertThrows(IncorrectQuotationsException.class, () -> parser.getNumberOfQuotations("256"));
	}

	@Test
	public void noIntegerQuotation()
	{
		assertThrows(IncorrectQuotationsException.class, () -> parser.getNumberOfQuotations("12.5"));
		assertThrows(IncorrectQuotationsException.class, () -> parser.getNumberOfQuotations("12,5"));
		assertThrows(IncorrectQuotationsException.class, () -> parser.getNumberOfQuotations("25test"));
		assertThrows(IncorrectQuotationsException.class, () -> parser.getNumberOfQuotations("test"));
	}
}
