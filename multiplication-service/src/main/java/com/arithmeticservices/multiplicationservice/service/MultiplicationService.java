package com.arithmeticservices.multiplicationservice.service;

import com.arithmeticservices.multiplicationservice.bean.Multiplication;

import java.math.BigDecimal;

public interface MultiplicationService {

    Multiplication multiply(BigDecimal leftOpd, BigDecimal rightOpd);
}
