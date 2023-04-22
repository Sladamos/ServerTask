package com.dynatrace.internship.parsers;

import com.dynatrace.internship.exceptions.IncorrectDateException;
import com.dynatrace.internship.exceptions.IncorrectDateFormatterException;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class DateParserImpl implements DateParser {
    private DateTimeFormatter formatter;

    @Override
    public LocalDate getFormattedDate(String date) {
        try {
            return LocalDate.parse(date, formatter);
        }
        catch (NullPointerException err)
        {
            throw new IncorrectDateFormatterException(err.getMessage());
        }
        catch (Exception err)
        {
            throw new IncorrectDateException(err.getMessage());
        }
    }
}
