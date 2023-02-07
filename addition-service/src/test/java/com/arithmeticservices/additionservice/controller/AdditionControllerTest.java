package com.arithmeticservices.additionservice.controller;

import com.arithmeticservices.additionservice.Addition;
import com.arithmeticservices.additionservice.service.AdditionService;
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
@WebMvcTest(value = AdditionController.class)
class AdditionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdditionService service;

    @Test
    @DisplayName("Initial Test")
    void initTest() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(10);
        BigDecimal rightOpd = BigDecimal.valueOf(20);
        Addition mockAdd = new Addition(leftOpd, rightOpd, leftOpd.add(rightOpd), "Addition(+)");
        Mockito.when(service.add(Mockito.any(), Mockito.any())).thenReturn(mockAdd);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/addition/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:10,rightOpd:20,answer:30,operation:Addition(+)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("should Add Two Negative Numbers")
    void shouldAddTwoNegativeNumbers() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(-10);
        BigDecimal rightOpd = BigDecimal.valueOf(-20);
        Addition mockAdd = new Addition(leftOpd, rightOpd, leftOpd.add(rightOpd), "Addition(+)");
        Mockito.when(service.add(Mockito.any(), Mockito.any())).thenReturn(mockAdd);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/addition/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:-10,rightOpd:-20,answer:-30,operation:Addition(+)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("should Subtract If One Of The Variable Is Negative")
    void shouldSubtractIfOneOfTheVariableIsNegative() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(10);
        BigDecimal rightOpd = BigDecimal.valueOf(-20);
        Addition mockAdd = new Addition(leftOpd, rightOpd, leftOpd.add(rightOpd), "Addition(+)");
        Mockito.when(service.add(Mockito.any(), Mockito.any())).thenReturn(mockAdd);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/addition/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:10,rightOpd:-20,answer:-10,operation:Addition(+)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    @DisplayName("should Subtract If One Of Variable Has Negative Value(lower than other) But Answer should be Positive")
    void shouldSubtractIfOneOfVariableHasNegativeValueButAnswerShouldBePositive() throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(-10);
        BigDecimal rightOpd = BigDecimal.valueOf(20);
        Addition mockAdd = new Addition(leftOpd, rightOpd, leftOpd.add(rightOpd), "Addition(+)");
        Mockito.when(service.add(Mockito.any(), Mockito.any())).thenReturn(mockAdd);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arithmetic/addition/{leftOpd}/{rightOpd}", leftOpd, rightOpd).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{leftOpd:-10,rightOpd:20,answer:10,operation:Addition(+)}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }
}