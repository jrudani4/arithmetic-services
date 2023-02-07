package com.arithmeticservices.multiplicationservice.controller;

import com.arithmeticservices.multiplicationservice.Multiplication;
import com.arithmeticservices.multiplicationservice.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MultiplicationController {

    @Autowired
    private MultiplicationService service;

    @GetMapping("/arithmetic/multiply/{leftOpd}/{rightOpd}")
    public Multiplication multiplicationService(@PathVariable BigDecimal leftOpd, @PathVariable BigDecimal rightOpd) {
        return service.multiply(leftOpd, rightOpd);
    }
}
