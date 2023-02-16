package com.arithmeticservices.additionservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Addition {

    private BigDecimal leftOpd;
    private BigDecimal rightOpd;
    private BigDecimal answer;
    private String operation;
}
