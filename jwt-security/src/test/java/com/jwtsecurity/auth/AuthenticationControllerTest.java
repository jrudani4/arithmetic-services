package com.jwtsecurity.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtsecurity.config.JwtService;
import com.jwtsecurity.entity.User;
import com.jwtsecurity.entity.UserRole;
import com.jwtsecurity.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "fname1", "lname1", "user1@gmail.com", "SamplePassword1", UserRole.ADMIN);

        user2 = new User(2, "fname2", "lname2", "user2@gmail.com", "SamplePassword2", UserRole.USER);
    }

    @Test
    @DisplayName("It should SAVE THE USER in the system.")
    void registerUserTest() throws Exception {
        // mock
        when(authenticationService.register(any(RegisterRequest.class)))
                .thenReturn(AuthenticationResponse.builder().token("jay").build());
        when(passwordEncoder.encode(anyString())).thenReturn(user1.getPassword());

        // act & assert
        this.mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.token", is("jay")))
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    @DisplayName("It should authenticate the USER.")
    void authenticateUserTest() throws Exception {
        // mock
        when(authenticationService.authenticate(any(AuthenticationRequest.class)))
                .thenReturn(AuthenticationResponse.builder().token("jay").build());
        when(passwordEncoder.encode(anyString())).thenReturn(user1.getPassword());

        // act & assert
        this.mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.token", is("jay")))
                .andReturn().getResponse().getContentAsString();

    }
}