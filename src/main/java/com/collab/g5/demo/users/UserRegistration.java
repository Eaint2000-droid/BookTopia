package com.collab.g5.demo.users;

import com.collab.g5.demo.exceptions.users.InsertUserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

//discuss if we will put user registration separately from user controller
public class UserRegistration {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    @PostMapping("/post/{newUser}")
    public User newUser(@RequestBody User newUser) throws InsertUserException{
        String userEmail=newUser.getUseremail();
        if(userService.containsUser(userEmail)){
            throw new InsertUserException(userEmail);
        }

        User toadd = new User();

        toadd.setFname(newUser.getFname());
        toadd.setLname(newUser.getLname());
        toadd.setPassword(passwordEncoder.encode(newUser.getPassword()));
        toadd.setUseremail(newUser.getUseremail());
//        toadd.setRoles(Arrays.asList());
//        decide the logic of how to assign roles

        return userService.save(toadd);
    }

}
