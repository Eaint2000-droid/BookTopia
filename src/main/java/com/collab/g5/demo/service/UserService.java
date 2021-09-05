package com.collab.g5.demo.service;

import com.collab.g5.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User getUserByEmail(String Email);
}
