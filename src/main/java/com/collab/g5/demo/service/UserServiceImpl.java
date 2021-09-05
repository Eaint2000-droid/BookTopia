package com.collab.g5.demo.service;

import com.collab.g5.demo.entity.User;
import com.collab.g5.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email){
        User user = userRepository.findById(email).orElse(null);
        return user;
    }
}
