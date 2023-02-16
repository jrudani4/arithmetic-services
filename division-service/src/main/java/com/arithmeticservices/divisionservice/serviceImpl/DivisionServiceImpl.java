package com.arithmeticservices.divisionservice.serviceImpl;

import com.arithmeticservices.divisionservice.bean.Division;
import com.arithmeticservices.divisionservice.exception.DivideByZeroException;
import com.arithmeticservices.divisionservice.service.DivisionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class DivisionServiceImpl implements DivisionService {
    @Override
    public Division divide(BigDecimal leftOpd, BigDecimal rightOpd) throws DivideByZeroException {
        if (Objects.equals(rightOpd, BigDecimal.ZERO)) {
            throw new DivideByZeroException();
        }
        return new Division(leftOpd, rightOpd, leftOpd.divide(rightOpd), "Division(÷)");
    }
}
