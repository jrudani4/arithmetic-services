package com.arithmeticservices.multiplicationservice.serviceImpl;

import com.arithmeticservices.multiplicationservice.bean.Multiplication;
import com.arithmeticservices.multiplicationservice.service.MultiplicationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    @Override
    public Multiplication multiply(BigDecimal leftOpd, BigDecimal rightOpd) {
        return new Multiplication(leftOpd, rightOpd, leftOpd.multiply(rightOpd), "Multiplication (*)");
    }
}
