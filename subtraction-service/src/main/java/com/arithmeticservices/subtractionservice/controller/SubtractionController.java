package com.arithmeticservices.subtractionservice.controller;

import com.arithmeticservices.subtractionservice.Subtraction;
import com.arithmeticservices.subtractionservice.service.SubtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class SubtractionController {

    @Autowired
    private SubtractionService service;

    @GetMapping("/arithmetic/subtraction/{leftOpd}/{rightOpd}")
    public Subtraction subtractionService(@PathVariable BigDecimal leftOpd, @PathVariable BigDecimal rightOpd) {
        return service.subtract(leftOpd, rightOpd);
    }
}
