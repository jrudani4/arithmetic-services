package com.arithmeticservices.divisionservice.serviceImpl;

import com.arithmeticservices.divisionservice.Division;
import com.arithmeticservices.divisionservice.service.DivisionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DivisionServiceImpl implements DivisionService {
    @Override
    public Division divide(BigDecimal leftOpd, BigDecimal rightOpd) {
        return new Division(leftOpd, rightOpd, leftOpd.divide(rightOpd), "Division(÷)");
    }
}
