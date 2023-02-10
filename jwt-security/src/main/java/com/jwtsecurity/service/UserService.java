package com.jwtsecurity.service;

import com.jwtsecurity.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void deleteUser(Integer id);

    User updateUser(User user, Integer id);

    User getUserById(Integer id);
}
