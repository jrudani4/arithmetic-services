package com.arithmeticservices.additionservice.controller;

import com.arithmeticservices.additionservice.bean.Addition;
import com.arithmeticservices.additionservice.service.AdditionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class AdditionController {

    @Autowired
    private AdditionService service;

    @GetMapping("/arithmetic/addition/{leftOpd}/{rightOpd}")
    public Addition additionService(@PathVariable BigDecimal leftOpd, @PathVariable BigDecimal rightOpd) {
        log.info("inside additionService Method");
        return service.add(leftOpd, rightOpd);
    }
}
