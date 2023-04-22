package com.dynatrace.internship.parsers;

import java.time.LocalDate;

public interface DateParser {
    LocalDate getFormattedDate(String date);
}
