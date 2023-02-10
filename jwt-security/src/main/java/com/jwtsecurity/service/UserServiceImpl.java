package com.jwtsecurity.service;

import com.jwtsecurity.entity.User;
import com.jwtsecurity.exception.ResourceNotFoundException;
import com.jwtsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public User updateUser(User user, Integer id) {
        User existingUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(encoder.encode(user.getPassword()));
        existingUser.setRole(user.getRole());
        repository.save(existingUser);
        return existingUser;
    }

    @Override
    public User getUserById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
