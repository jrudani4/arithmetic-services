package com.arithmeticservices.divisionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Can't divide by zero")
public class DivideByZeroException extends ArithmeticException {

}
