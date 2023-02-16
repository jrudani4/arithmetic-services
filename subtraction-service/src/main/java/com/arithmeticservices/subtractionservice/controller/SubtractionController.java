package com.arithmeticservices.subtractionservice.controller;

import com.arithmeticservices.subtractionservice.bean.Subtraction;
import com.arithmeticservices.subtractionservice.service.SubtractionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class SubtractionController {

    @Autowired
    private SubtractionService service;

    @GetMapping("/arithmetic/subtraction/{leftOpd}/{rightOpd}")
    public Subtraction subtractionService(@PathVariable BigDecimal leftOpd, @PathVariable BigDecimal rightOpd) {
        log.info("inside subtractionService Method");
        return service.subtract(leftOpd, rightOpd);
    }
}
