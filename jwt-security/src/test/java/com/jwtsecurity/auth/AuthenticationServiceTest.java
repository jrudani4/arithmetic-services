package com.jwtsecurity.auth;

import com.jwtsecurity.config.JwtService;
import com.jwtsecurity.entity.User;
import com.jwtsecurity.entity.UserRole;
import com.jwtsecurity.exception.ResourceNotFoundException;
import com.jwtsecurity.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    private User user1;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "fname1", "lname1", "user1@gmail.com", "SamplePassword1", UserRole.ADMIN);
    }

    @Test
    @DisplayName("It should register the user")
    void registerUserTest() {
        // arrange / mock
        when(passwordEncoder.encode(anyString())).thenReturn("hashed-password");
        when(userRepository.save(any(User.class))).thenReturn(null);
        when(jwtService.generateToken(any(User.class))).thenReturn("token123");

        // act
        AuthenticationResponse authenticationResponse = authenticationService.register(RegisterRequest.builder()
                .firstName(user1.getFirstName())
                .lastName(user1.getLastName())
                .email(user1.getEmail())
                .password(user1.getPassword())
                .build());

        // assert
        assertEquals("token123", authenticationResponse.getToken().toString());

    }

    @Test
    @DisplayName("It should authenticate the user if user exists")
    void authenticateUserTest_success() {
        // arrange / mock
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user1));
        when(jwtService.generateToken(any(User.class))).thenReturn("token123");

        // act
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(AuthenticationRequest.builder()
                .email(user1.getEmail())
                .password(user1.getPassword())
                .build());

        // assert
        assertEquals("token123", authenticationResponse.getToken().toString());

    }

    @Test
    @DisplayName("It shouldn't authenticate the user if user doesn't exist")
    void authenticateUserTest_failure() {
        // arrange / mock
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // assert
        AuthenticationRequest build = AuthenticationRequest.builder()
                .email(user1.getEmail())
                .password(user1.getPassword())
                .build();
        assertThrows(ResourceNotFoundException.class, () -> {

            //act
            authenticationService.authenticate(build);
        });

    }
}