package com.dynatrace.internship;

import com.dynatrace.internship.exceptions.IncorrectDateException;
import com.dynatrace.internship.exceptions.IncorrectDateFormatterException;
import com.dynatrace.internship.parsers.DateParser;
import com.dynatrace.internship.parsers.DateParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DateParserTests {

    private DateTimeFormatter formatter;
    private DateParser parser;

    @BeforeEach
    public void initializeFormatter()
    {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        parser = new DateParserImpl(formatter);
    }

    @Test
    public void wrongFormats()
    {
        assertThrows(IncorrectDateException.class, () -> parser.getFormattedDate("2021-03-12 15:22"));
        assertThrows(IncorrectDateException.class, () -> parser.getFormattedDate("03-2022-12"));
        assertThrows(IncorrectDateException.class, () -> parser.getFormattedDate("03-12-2022"));
    }

    @Test
    public void noDateArgument()
    {
        assertThrows(IncorrectDateException.class, () -> parser.getFormattedDate("No date"));
    }

    @Test
    public void noFormatter()
    {
        parser = new DateParserImpl(null);
        assertThrows(IncorrectDateFormatterException.class, () -> parser.getFormattedDate("2021-03-12"));
    }

    @Test
    public void incorrectDateNumber()
    {
        assertThrows(IncorrectDateException.class, () -> parser.getFormattedDate("2021-13-12"));
        assertEquals(parser.getFormattedDate("2021-04-31"), parser.getFormattedDate("2021-04-30"));
        assertEquals(parser.getFormattedDate("2021-02-29"), parser.getFormattedDate("2021-02-28"));
    }

    @Test
    public void correctDate()
    {
        String date = "2021-03-12";
        parser.getFormattedDate(date);
    }
}
