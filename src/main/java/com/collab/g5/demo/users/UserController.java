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
    @Autowired
    private UserService userService;
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
    @GetMapping("/get/{email}")
    public User getUserByEmail(@PathVariable String email){
        User user=userService.getUserByEmail(email);

        if(user==null){
            // throw an exception
            throw new UserNotFoundException(email);
        }
        return user;
    }


    @DeleteMapping("/del/{email}")
    void deleteUser(@PathVariable String email ){
        User user= userService.getUserByEmail(email);
        if(user==null){
            // throw an exception
            throw new UserNotFoundException(email);
        }
        userService.delete(user);
    }



//    @PutMapping("updatePassword/{email}")
//    public void updatePassword(@PathVariable String email){
//
//    }


}
