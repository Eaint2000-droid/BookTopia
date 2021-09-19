package com.collab.g5.demo.users;

import com.collab.g5.demo.exceptions.users.EmailExistsException;
import com.collab.g5.demo.exceptions.users.InsertUserException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
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


    @DeleteMapping("/del/{email}")
    void deleteUser(@PathVariable String email ){
        User user= userService.getUserByEmail(email);
        if(user==null){
            // throw an exception
            throw new UserNotFoundException(email);
        }
        userService.delete(user);
    }


//    @PostMapping("/post/{newUser}")
//    public User newUser(@RequestBody User newUser){
//        String userEmail=newUser.getUseremail();
//        if(userService.containsUser(userEmail)){
//            throw new InsertUserException(userEmail);
//        }
////        User toadd = new User();
////
////        toadd.setFname(user.getFname());
////        toadd.setLname(user.getLname());
////        toadd.setPassword(passwordEncoder.encode(accountDto.getPassword()));
////        toadd.setEmail(accountDto.getEmail());
////
////        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
////        userService.save(user);
//        return userService.save(newUser);
//    }



}
