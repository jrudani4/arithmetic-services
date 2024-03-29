package com.arithmeticservices.divisionservice.controller;

import com.arithmeticservices.divisionservice.bean.Division;
import com.arithmeticservices.divisionservice.service.DivisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class DivisionController {

    @Autowired
    private DivisionService service;

    @GetMapping("/arithmetic/division/{leftOpd}/{rightOpd}")
    public Division divisionService(@PathVariable BigDecimal leftOpd, @PathVariable BigDecimal rightOpd) {
        log.info("inside divisionService Method");
        return service.divide(leftOpd, rightOpd);
    }
}
