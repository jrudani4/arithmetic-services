package com.arithmeticservices.multiplicationservice;

import com.arithmeticservices.multiplicationservice.bean.Multiplication;
import com.arithmeticservices.multiplicationservice.controller.MultiplicationController;
import com.arithmeticservices.multiplicationservice.service.MultiplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MultiplicationController.class)
class MultiplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MultiplicationService service;

    @Autowired
    private ObjectMapper objectMapper;

    static Stream<Arguments> inputs() {
        return Stream.of(
                arguments(10, 20),
                arguments(-10, -20),
                arguments(10, -20),
                arguments(-10, 20),
                arguments(10, 0)
        );
    }

    @ParameterizedTest
    @DisplayName("All Test")
    @MethodSource("inputs")
    void initTest(int leftOp, int rightOp) throws Exception {
        BigDecimal leftOpd = BigDecimal.valueOf(leftOp);
        BigDecimal rightOpd = BigDecimal.valueOf(rightOp);
        Multiplication mockMultiply = new Multiplication(leftOpd, rightOpd, leftOpd.multiply(rightOpd), "Multiplication(*)");
        Mockito.when(service.multiply(Mockito.any(), Mockito.any())).thenReturn(mockMultiply);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/arithmetic/multiply/{leftOpd}/{rightOpd}", leftOpd, rightOpd))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.leftOpd", is(mockMultiply.getLeftOpd().intValue())))
                .andExpect(jsonPath("$.rightOpd", is(mockMultiply.getRightOpd().intValue())))
                .andExpect(jsonPath("$.answer", is(mockMultiply.getAnswer().intValue())))
                .andExpect(jsonPath("$.operation", is(mockMultiply.getOperation())))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}