package com.arithmeticservices.subtractionservice.service;

import com.arithmeticservices.subtractionservice.bean.Subtraction;

import java.math.BigDecimal;

public interface SubtractionService {

    Subtraction subtract(BigDecimal leftOpd, BigDecimal rightOpd);
}
