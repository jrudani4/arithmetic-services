package com.jwtsecurity.entity;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void deleteUser(Integer id);

    User updateUser(User user, Integer id);
}
