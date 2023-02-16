package com.arithmeticservices.additionservice.service;

import com.arithmeticservices.additionservice.bean.Addition;

import java.math.BigDecimal;

public interface AdditionService {

    Addition add(BigDecimal leftOpd, BigDecimal rightOpd);
}
