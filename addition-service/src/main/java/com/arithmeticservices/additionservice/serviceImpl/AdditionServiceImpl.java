package com.arithmeticservices.additionservice.serviceImpl;

import com.arithmeticservices.additionservice.Addition;
import com.arithmeticservices.additionservice.service.AdditionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdditionServiceImpl implements AdditionService {
    @Override
    public Addition add(BigDecimal leftOpd, BigDecimal rightOpd) {
        return new Addition(leftOpd, rightOpd, leftOpd.add(rightOpd), "Addition(+)");
    }
}
