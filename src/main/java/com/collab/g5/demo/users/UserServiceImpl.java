package com.collab.g5.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean containsUser(String userEmail) {
        return userRepository.findById(userEmail)!=null;
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}
