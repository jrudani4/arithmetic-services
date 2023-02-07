package com.arithmeticservices.subtractionservice.serviceImpl;

import com.arithmeticservices.subtractionservice.Subtraction;
import com.arithmeticservices.subtractionservice.service.SubtractionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SubtractionServiceImpl implements SubtractionService {
    @Override
    public Subtraction subtract(BigDecimal leftOpd, BigDecimal rightOpd) {
        return new Subtraction(leftOpd, rightOpd, leftOpd.subtract(rightOpd), "Subtraction(-)");
    }
}
