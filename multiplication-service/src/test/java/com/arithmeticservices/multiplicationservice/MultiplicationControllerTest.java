package com.arithmeticservices.multiplicationservice;

import com.arithmeticservices.multiplicationservice.controller.MultiplicationController;
import com.arithmeticservices.multiplicationservice.service.MultiplicationService;
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
@WebMvcTest(value = MultiplicationController.class)
class MultiplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MultiplicationService service;

    @Test
    @DisplayName("Initial Test")
    void initTest() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(10);
        BigDecimal rightOpd = BigDecimal.valueOf(10);
        Multiplication mockMultiply = new Multiplication(leftOpd, rightOpd, leftOpd.multiply(rightOpd), "Multiplication(*)");
        Mockito.when(service.multiply(Mockito.any(), Mockito.any())).thenReturn(mockMultiply);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/multiply/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:10,rightOpd:10,answer:100,operation:Multiplication(*)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if One Of The Value Is Negative Answer Should Be Negative")
    void ifOneOfTheValueIsNegativeAnswerShouldBeNegative() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(10);
        BigDecimal rightOpd = BigDecimal.valueOf(-10);
        Multiplication mockMultiply = new Multiplication(leftOpd, rightOpd, leftOpd.multiply(rightOpd), "Multiplication(*)");
        Mockito.when(service.multiply(Mockito.any(), Mockito.any())).thenReturn(mockMultiply);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/multiply/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:10,rightOpd:-10,answer:-100,operation:Multiplication(*)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if Both Of The Values Are Negative Answer Should Be Positive")
    void ifBothOfTheValuesAreNegativeAnswerShouldBePositive() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(-10);
        BigDecimal rightOpd = BigDecimal.valueOf(-10);
        Multiplication mockMultiply = new Multiplication(leftOpd, rightOpd, leftOpd.multiply(rightOpd), "Multiplication(*)");
        Mockito.when(service.multiply(Mockito.any(), Mockito.any())).thenReturn(mockMultiply);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/multiply/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:-10,rightOpd:-10,answer:100,operation:Multiplication(*)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if One Of The Value Is Zero Answer Should Be Zero")
    void ifOneOfTheValueIsZeroAnswerShouldBeZero() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(10);
        BigDecimal rightOpd = BigDecimal.valueOf(0);
        Multiplication mockMultiply = new Multiplication(leftOpd, rightOpd, leftOpd.multiply(rightOpd), "Multiplication(*)");
        Mockito.when(service.multiply(Mockito.any(), Mockito.any())).thenReturn(mockMultiply);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/multiply/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:10,rightOpd:0,answer:0,operation:Multiplication(*)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }
}