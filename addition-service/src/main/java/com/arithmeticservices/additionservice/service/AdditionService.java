package com.arithmeticservices.additionservice.service;

import com.arithmeticservices.additionservice.Addition;

import java.math.BigDecimal;

public interface AdditionService {

    Addition add(BigDecimal leftOpd, BigDecimal rightOpd);
}
