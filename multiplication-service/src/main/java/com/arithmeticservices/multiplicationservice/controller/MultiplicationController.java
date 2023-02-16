package com.arithmeticservices.multiplicationservice.controller;

import com.arithmeticservices.multiplicationservice.bean.Multiplication;
import com.arithmeticservices.multiplicationservice.service.MultiplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class MultiplicationController {

    @Autowired
    private MultiplicationService service;

    @GetMapping("/arithmetic/multiply/{leftOpd}/{rightOpd}")
    public Multiplication multiplicationService(@PathVariable BigDecimal leftOpd, @PathVariable BigDecimal rightOpd) {
        log.info("inside multiplicationService Method");
        return service.multiply(leftOpd, rightOpd);
    }
}
