package com.dynatrace.internship.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangesController {

    @GetMapping(value = {"/exchanges/*", "/exchanges"})
    public String generateError() {
        return "Specify correct currency code and date";
    }

    @GetMapping(value = "/exchanges/{code}/{date}")
    public String getExchangeRate(@PathVariable("code") String currencyCode, @PathVariable("date") String date)
    {
        //is code okay
        //is date okay
        //200 okay
        //select
        return currencyCode + date;
    }
}
