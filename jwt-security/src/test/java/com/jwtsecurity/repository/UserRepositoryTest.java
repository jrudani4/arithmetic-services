package com.jwtsecurity.repository;

import com.jwtsecurity.entity.User;
import com.jwtsecurity.entity.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // demo records
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "fname1", "lname1", "user1@gmail.com", "SamplePassword1", UserRole.ADMIN);
        user2 = new User(2, "fname2", "lname2", "user2@gmail.com", "SamplePassword2", UserRole.USER);
        userRepository.save(user1);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("It should SAVE THE RECORD in the database.")
    void saveTest() {
        // act
        User user = userRepository.save(user2);

        // assertion
        assertNotNull(user);
//        assertEquals(2, user.getId());
        assertNotNull(user.getId());
        assertEquals("fname2", user2.getFirstName());
        assertEquals("lname2", user2.getLastName());
        assertEquals("user2@gmail.com", user.getEmail());
        assertEquals("SamplePassword2", user.getPassword());
        assertEquals(UserRole.USER, user.getRole());
    }

    @Test
    @DisplayName("It should FIND USER BY USERNAME, if the user is available.")
    void findByUsernameTest_success() {
        // arrange
        userRepository.save(user2);

        // act
        Optional<User> optionalUser = userRepository.findByEmail("user2@gmail.com");

        // assert
        assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();
//        assertEquals(2L, user.getId());
        assertNotNull(user.getId());
        assertEquals("fname2", user2.getFirstName());
        assertEquals("lname2", user2.getLastName());
        assertEquals("user2@gmail.com", user.getEmail());
        assertEquals("SamplePassword2", user.getPassword());
        assertEquals(UserRole.USER, user.getRole());

    }

    @Test
    @DisplayName("FIND BY USERNAME - It should return empty object, if the user isn't available.")
    void findByUsernameTest_failure() {
        // act
        Optional<User> optionalUser = userRepository.findByEmail("user2@gmail.com");

        // assert
        assertTrue(optionalUser.isEmpty());
    }

    @Test
    @DisplayName("It should FIND ALL USERS")
    void getAllUserTest() {
        // arrange
        userRepository.save(user2);

        // act
        List<User> list = userRepository.findAll();

        // assert
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("It should FIND USER BY ID, if the user is available.")
    void findByIdTest_success() {
        // arrange
        User savedUser = userRepository.save(user2);

        // act
        Optional<User> optionalUser = userRepository.findById(savedUser.getId());

        // assert
        assertTrue(optionalUser.isPresent());

        User fetchedUser = optionalUser.get();
        assertNotNull(fetchedUser.getId());
        assertEquals(savedUser.getId(), fetchedUser.getId());
        assertEquals("fname2", fetchedUser.getFirstName());
        assertEquals("lname2", fetchedUser.getLastName());
        assertEquals("user2@gmail.com", fetchedUser.getEmail());
        assertEquals("SamplePassword2", fetchedUser.getPassword());
        assertEquals(UserRole.USER, fetchedUser.getRole());

    }

    @Test
    @DisplayName("FIND BY ID - It should return empty object, if the user isn't available.")
    void findByIdTest_failure() {
        // act
        Optional<User> optionalUser = userRepository.findById(Integer.MAX_VALUE); // large long value that doesn't exist in database

        // assert
        assertTrue(optionalUser.isEmpty());
    }


    @Test
    @DisplayName("It should UPDATE THE USER, if user exists")
    void updateUserTest() {
        // arrange
        User savedUser = userRepository.save(user2);

        // act
        User existingUser = userRepository.findById(savedUser.getId()).get();
        existingUser.setFirstName("new_firstname");
        User updateUser = userRepository.save(existingUser);

        // assert
        assertEquals("new_firstname", updateUser.getFirstName());

    }

    @Test
    @DisplayName("It should DELETE THE USER")
    void deleteUserTest() {
        // arrange
        User savedUser = userRepository.save(user2);

        // act
        userRepository.delete(savedUser);

        // assert
        Optional<User> optionalUser = userRepository.findById(savedUser.getId());
        assertFalse(optionalUser.isPresent());

    }

}