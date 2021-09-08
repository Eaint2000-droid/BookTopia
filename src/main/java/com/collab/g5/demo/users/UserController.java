package com.collab.g5.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){
        List<User> toReturn=userService.getAllUsers();

        if(toReturn.size()==0){
            throw new UserNotFoundException();
        }

        return toReturn;
    }


    @GetMapping("/get/{email}")
    public User getUserById(@PathVariable String email){
        User user=userService.getUserByEmail(email);

        if(user==null){
            // throw an exception
            throw new UserNotFoundException(email);
        }
        return user;
    }

    @PostMapping("/{newUser}")
    public User newUser(@RequestBody User newUser){
        String userEmail=newUser.getUseremail();
        if(userService.containsUser(userEmail)){
            throw new InsertUserException(userEmail);
        }
        return userService.save(newUser);
    }

    @DeleteMapping("/del/{email}")
    void deleteUser(@PathVariable String email ){
        User user= userService.getUserByEmail(email);
        if(user!=null){
            // throw an exception
            throw new UserNotFoundException(email);
        }
        userService.delete(user);
    }




}
