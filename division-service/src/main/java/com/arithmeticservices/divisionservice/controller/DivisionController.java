package com.arithmeticservices.divisionservice.controller;

import com.arithmeticservices.divisionservice.Division;
import com.arithmeticservices.divisionservice.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class DivisionController {

    @Autowired
    private DivisionService service;

    @GetMapping("/arithmetic/division/{leftOpd}/{rightOpd}")
    public Division divisionService(@PathVariable BigDecimal leftOpd, @PathVariable BigDecimal rightOpd) {
        return service.divide(leftOpd, rightOpd);
    }
}
