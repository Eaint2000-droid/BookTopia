package com.collab.g5.demo.users;

import com.collab.g5.demo.exceptions.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //HR METHODS
    //To view all employees records
    @GetMapping("/hr/getAll")
    public List<User> getUsers(){
        List<User> toReturn=userServiceImpl.getAllUsers();
        if(toReturn.size()==0){
            throw new UserNotFoundException();
        }
        return toReturn;
    }

    //To add new user
    @PostMapping("/hr/create/{newUser}")
    public void newUser(@RequestBody User newUser){
        userServiceImpl.addNewUser(newUser);
    }

    //EMPLOYEE METHODS

}
