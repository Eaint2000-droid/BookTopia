package com.collab.g5.demo.controller;

import com.collab.g5.demo.entity.User;
import com.collab.g5.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){

        return userService.getAllUsers();
    }


    @GetMapping("/{email}")
    public User getUserById(@PathVariable String email){
        User user=userService.getUserByEmail(email);
        return user;
    }




}
