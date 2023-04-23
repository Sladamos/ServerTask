package com.dynatrace.internship.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontController {

    @GetMapping(value = {"", "/**"})
    public String incorrectValue()
    {
        return "Enter correct URL specified in .README file";
    }
}
