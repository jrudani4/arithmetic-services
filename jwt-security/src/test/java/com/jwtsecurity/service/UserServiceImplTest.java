package com.jwtsecurity.service;

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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    // demo records
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "fname1", "lname1", "user1@gmail.com", "SamplePassword1", UserRole.ADMIN);

        user2 = new User(2, "fname2", "lname2", "user2@gmail.com", "SamplePassword2", UserRole.USER);
    }

    @Test
    @DisplayName("It should return all the users")
    void getAllUsersTest() {

        // mock
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        // act
        List<User> allUsers = userService.getAllUsers();

        // assert
        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
    }

    @Test
    @DisplayName("It should delete the user")
    void deleteUserTest() {
        // mock
        doNothing()
                .when(userRepository)
                .deleteById(anyInt());

        // act
        userService.deleteUser(user1.getId());

        // assert / verify
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    @DisplayName("It should update the user")
    void updateUser_success() {
        // mock
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user1));
        // when(userRepository.save(any(User.class))).thenReturn(user1);

        // act
        user1.setFirstName("new_username");
        User updatedUser = userService.updateUser(user1, user1.getId());

        // assert
        assertNotNull(updatedUser);
        assertEquals("new_username", user1.getFirstName());
    }

    @Test
    @DisplayName("In case of User NOTFOUND, it should throw ResourceNotFoundException")
    void updateUser_failure() {
        // mock
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        // act & assert
        user1.setFirstName("new_username");
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUser(user1,Integer.MAX_VALUE); // any long userId thats doesn't contain by db
        });
    }
}