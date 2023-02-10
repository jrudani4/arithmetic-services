package com.jwtsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtsecurity.auth.AuthenticationService;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

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

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "fname1", "lname1", "user1@gmail.com", "SamplePassword1", UserRole.ADMIN);

        user2 = new User(2, "fname2", "lname2", "user2@gmail.com", "SamplePassword2", UserRole.USER);
    }

    @Test
    @DisplayName("It should RETRIEVE ALL USERS from the system.")
    void getAllUsers() throws Exception {
        // mock
        List<User> userList = List.of(user1, user2);
        when(userService.getAllUsers()).thenReturn(userList);

        // act & assert
        this.mockMvc.perform(get("/users/view-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));

    }

    @Test
    @DisplayName("It should DELETE THE USER with specified Id.")
    void deleteUserById() throws Exception {
        doNothing().when(userService).deleteUser(anyInt());

        this.mockMvc.perform(delete("/users/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("User deleted successfully.")));
    }

    @Test
    @DisplayName("It should UPDATE THE USER with provided details")
    void updateUser() throws Exception {
        // mock
        when(userService.updateUser(any(User.class), anyInt())).thenReturn(user2);

        // act & assert
        this.mockMvc.perform(put("/users/update/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.firstName", is(user2.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user2.getLastName())))
                .andExpect(jsonPath("$.email", is(user2.getEmail())))
                .andExpect(jsonPath("$.password", is(user2.getPassword())))
                .andExpect(jsonPath("$.role", is(user2.getRole().toString())));
    }
}