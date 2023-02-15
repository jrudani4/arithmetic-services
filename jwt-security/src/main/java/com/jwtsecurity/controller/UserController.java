package com.jwtsecurity.controller;

import com.jwtsecurity.entity.User;
import com.jwtsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/view-all")
    public List<User> getAllUsers() {
        log.info("inside getAllUsers() Method");
        return userService.getAllUsers();
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        log.info("inside getUserById-" + id + " Method");
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        log.info("inside updateUser-" + id + " Method");
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        userService.deleteUser(id);
        log.info("inside deleteUserById-" + id + " Method");
        return new ResponseEntity<String>("User deleted successfully.", HttpStatus.OK);
    }
}
