package com.arithmeticservices.divisionservice;

import java.math.BigDecimal;

public class Division {

    private BigDecimal leftOpd;
    private BigDecimal rightOpd;
    private BigDecimal answer;
    private String operation;

    public Division() {
    }

    public Division(BigDecimal leftOpd, BigDecimal rightOpd, BigDecimal answer, String operation) {
        this.leftOpd = leftOpd;
        this.rightOpd = rightOpd;
        this.answer = answer;
        this.operation = operation;
    }

    public BigDecimal getLeftOpd() {
        return leftOpd;
    }

    public void setLeftOpd(BigDecimal leftOpd) {
        this.leftOpd = leftOpd;
    }

    public BigDecimal getRightOpd() {
        return rightOpd;
    }

    public void setRightOpd(BigDecimal rightOpd) {
        this.rightOpd = rightOpd;
    }

    public BigDecimal getAnswer() {
        return answer;
    }

    public void setAnswer(BigDecimal answer) {
        this.answer = answer;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
