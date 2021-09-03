package com.collab.g5.demo.controller;

import com.collab.g5.demo.entity.User;
import com.collab.g5.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
>>>>>>> 0ae9450470517a876ad6033a43645475ce8744cb
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
        if (user==null){
//            throw exception
            return null;
        }
        return user;
    }




}
