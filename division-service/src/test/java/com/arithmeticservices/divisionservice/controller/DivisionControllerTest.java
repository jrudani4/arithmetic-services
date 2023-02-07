package com.arithmeticservices.divisionservice.controller;

import com.arithmeticservices.divisionservice.Division;
import com.arithmeticservices.divisionservice.service.DivisionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DivisionController.class)
class DivisionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DivisionService service;

    @Test
    @DisplayName("Initial Test")
    void initTest() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(12);
        BigDecimal rightOpd = BigDecimal.valueOf(3);
        Division mockDivide = new Division(leftOpd, rightOpd, leftOpd.divide(rightOpd), "Division()");
        Mockito.when(service.divide(Mockito.any(), Mockito.any())).thenReturn(mockDivide);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/division/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:12,rightOpd:3,answer:4,operation:Division()}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if Both Of The Values Are Negative Answer Should Be Positive")
    void ifBothOfTheValuesAreNegativeAnswerShouldBePositive() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(-12);
        BigDecimal rightOpd = BigDecimal.valueOf(-3);
        Division mockDivide = new Division(leftOpd, rightOpd, leftOpd.divide(rightOpd), "Division()");
        Mockito.when(service.divide(Mockito.any(), Mockito.any())).thenReturn(mockDivide);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/division/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:-12,rightOpd:-3,answer:4,operation:Division()}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if One Of The Value Is Negative Answer Should Be Negative")
    void ifOneOfTheValueIsNegativeAnswerShouldBeNegative() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(12);
        BigDecimal rightOpd = BigDecimal.valueOf(-3);
        Division mockDivide = new Division(leftOpd, rightOpd, leftOpd.divide(rightOpd), "Division()");
        Mockito.when(service.divide(Mockito.any(), Mockito.any())).thenReturn(mockDivide);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/division/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:12,rightOpd:-3,answer:-4,operation:Division()}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if Numerator Is Zero Then Answer Should Be Zero")
    void ifNumeratorIsZeroThenAnswerShouldBeZero() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(0);
        BigDecimal rightOpd = BigDecimal.valueOf(2);
        Division mockDivide = new Division(leftOpd, rightOpd, leftOpd.divide(rightOpd), "Division()");
        Mockito.when(service.divide(Mockito.any(), Mockito.any())).thenReturn(mockDivide);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/division/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:0,rightOpd:2,answer:0,operation:Division()}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }
}