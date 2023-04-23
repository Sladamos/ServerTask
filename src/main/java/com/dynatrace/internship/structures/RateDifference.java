package com.dynatrace.internship.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class RateDifference {
    private LocalDate differenceDate;
    private double differenceValue;
}
