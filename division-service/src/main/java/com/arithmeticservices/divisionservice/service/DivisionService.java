package com.arithmeticservices.divisionservice.service;

import com.arithmeticservices.divisionservice.bean.Division;

import java.math.BigDecimal;

public interface DivisionService {

    Division divide(BigDecimal leftOpd, BigDecimal rightOpd);
}
