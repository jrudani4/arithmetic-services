package com.arithmeticservices.subtractionservice.controller;

import com.arithmeticservices.subtractionservice.Subtraction;
import com.arithmeticservices.subtractionservice.service.SubtractionService;
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
@WebMvcTest(value = SubtractionController.class)
class SubtractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubtractionService service;

    @Test
    @DisplayName("Initial Test")
    void initTest() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(5);
        BigDecimal rightOpd = BigDecimal.valueOf(2);
        Subtraction mockSubtract = new Subtraction(leftOpd, rightOpd, leftOpd.subtract(rightOpd), "Subtraction(-)");
        Mockito.when(service.subtract(Mockito.any(), Mockito.any())).thenReturn(mockSubtract);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/subtraction/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:5,rightOpd:2,answer:3,operation:Subtraction(-)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if Both Values Are Negative And RightOpd Has Lower Value Then Answer Should Be Negative")
    void ifBothValuesAreNegativeAndRightOpdHasLowerValueThenAnswerShouldBeNegative() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(-5);
        BigDecimal rightOpd = BigDecimal.valueOf(-2);
        Subtraction mockSubtract = new Subtraction(leftOpd, rightOpd, leftOpd.subtract(rightOpd), "Subtraction(-)");
        Mockito.when(service.subtract(Mockito.any(), Mockito.any())).thenReturn(mockSubtract);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/subtraction/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:-5,rightOpd:-2,answer:-3,operation:Subtraction(-)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if Both Values Are Negative And LeftOpd Has Lower Value Then Answer Should Be Positive")
    void ifBothValuesAreNegativeAndLeftOpdHasLowerValueThenAnswerShouldBePositive() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(-2);
        BigDecimal rightOpd = BigDecimal.valueOf(-5);
        Subtraction mockSubtract = new Subtraction(leftOpd, rightOpd, leftOpd.subtract(rightOpd), "Subtraction(-)");
        Mockito.when(service.subtract(Mockito.any(), Mockito.any())).thenReturn(mockSubtract);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/subtraction/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:-2,rightOpd:-5,answer:3,operation:Subtraction(-)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if LeftOpd Is Negative And LeftOpd Has Lower Value Then Answer Should Be Negative")
    void ifLeftOpdIsNegativeAndLeftOpdHasLowerValueThenAnswerShouldBeNegative() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(-2);
        BigDecimal rightOpd = BigDecimal.valueOf(5);
        Subtraction mockSubtract = new Subtraction(leftOpd, rightOpd, leftOpd.subtract(rightOpd), "Subtraction(-)");
        Mockito.when(service.subtract(Mockito.any(), Mockito.any())).thenReturn(mockSubtract);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/subtraction/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:-2,rightOpd:5,answer:-7,operation:Subtraction(-)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if RightOpd Is Negative Then Answer Should Be Positive")
    void ifRightOpdIsNegativeThenAnswerShouldBePositive() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(2);
        BigDecimal rightOpd = BigDecimal.valueOf(-5);
        Subtraction mockSubtract = new Subtraction(leftOpd, rightOpd, leftOpd.subtract(rightOpd), "Subtraction(-)");
        Mockito.when(service.subtract(Mockito.any(), Mockito.any())).thenReturn(mockSubtract);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/subtraction/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:2,rightOpd:-5,answer:7,operation:Subtraction(-)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("if Both Values Are Positive And RightOpd Has Higher Value Then Answer Should Be Negative")
    void ifBothValuesArePositiveAndRightOpdHasHigherValueThenAnswerShouldBeNegative() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(2);
        BigDecimal rightOpd = BigDecimal.valueOf(6);
        Subtraction mockSubtract = new Subtraction(leftOpd, rightOpd, leftOpd.subtract(rightOpd), "Subtraction(-)");
        Mockito.when(service.subtract(Mockito.any(), Mockito.any())).thenReturn(mockSubtract);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/subtraction/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:2,rightOpd:6,answer:-4,operation:Subtraction(-)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }
}