package com.arithmeticservices.divisionservice.controller;

import com.arithmeticservices.divisionservice.bean.Division;
import com.arithmeticservices.divisionservice.exception.DivideByZeroException;
import com.arithmeticservices.divisionservice.service.DivisionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DivisionController.class)
class DivisionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DivisionService service;

    @Autowired
    private ObjectMapper objectMapper;

    static Stream<Arguments> inputs() {
        return Stream.of(
                arguments(12, 3),
                arguments(12, -3),
                arguments(-12, -3),
                arguments(0, 2)
        );
    }

    @ParameterizedTest
    @DisplayName("All Test")
    @MethodSource("inputs")
    void initTest(int leftOp, int rightOp) throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(leftOp);
        BigDecimal rightOpd = BigDecimal.valueOf(rightOp);
        Division division = new Division(leftOpd, rightOpd, leftOpd.divide(rightOpd), "Division(/)");
        Mockito.when(service.divide(Mockito.any(), Mockito.any())).thenReturn(division);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/arithmetic/division/{leftOpd}/{rightOpd}", leftOpd, rightOpd))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.leftOpd", is(division.getLeftOpd().intValue())))
                .andExpect(jsonPath("$.rightOpd", is(division.getRightOpd().intValue())))
                .andExpect(jsonPath("$.answer", is(division.getAnswer().intValue())))
                .andExpect(jsonPath("$.operation", is(division.getOperation())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("should throw ArithmeticException if denominator is zero")
    void shouldThrowArithmeticExceptionIfDenominatorIsZero() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(2);
        BigDecimal rightOpd = BigDecimal.valueOf(0);
        Mockito.doThrow(DivideByZeroException.class).when(service).divide(leftOpd, rightOpd);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/arithmetic/division/{leftOpd}/{rightOpd}", leftOpd, rightOpd))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }
}