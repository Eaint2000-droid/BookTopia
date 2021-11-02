package com.collab.g5.demo.users;

import com.collab.g5.demo.exceptions.users.EmailExistsException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userServiceImpl;
    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl=userServiceImpl;
    }

    //HR METHODS
    //To view all employees records
    @GetMapping("/hr/getAll/")
    public List<User> getUsers() throws UserNotFoundException{
        List<User> toReturn = userServiceImpl.getAllUsers();
        if (toReturn.size() == 0) {
            throw new UserNotFoundException();
        }

        return toReturn;
    }

    //To add new user
    @PostMapping("/hr/create/")
    public void newUser(@RequestBody User newUser) throws EmailExistsException{
        try {
            User userExists = getUserByEmail(newUser.getEmail());
            throw new EmailExistsException("email already taken");
        }catch(UsernameNotFoundException e){
            userServiceImpl.addNewUser(newUser);
        }

    }

    //EMPLOYEE METHODS
    @GetMapping("/get/{email}")
    public User getUserByEmail(@PathVariable String email) throws UsernameNotFoundException{
        System.out.println(email);
        if(userServiceImpl.getUserByEmail(email)==null){
            System.out.println("Not Found");
            throw new UsernameNotFoundException("Email not found " + email);
        }
        System.out.println("EMAIL");
        System.out.println(userServiceImpl.getUserByEmail(email));
        return userServiceImpl.getUserByEmail(email);
    }




    @DeleteMapping("/del/{email}")
    void deleteUser(@PathVariable String email) throws UserNotFoundException{
        User user = userServiceImpl.getUserByEmail(email);
        if (user == null) {
            // throw an exception
            throw new UserNotFoundException(email);
        }
        userServiceImpl.delete(user);
    }

    @PutMapping("/update/fname/{fname}")
    User updateFname(@PathVariable String fname, @Valid @RequestBody User user) throws UserNotFoundException {


        User checkUser= userServiceImpl.updateFname(fname, user);

        if (checkUser == null) {
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }

        return checkUser;

    }

    @PutMapping("/update/Lname/{lname}")
    User updateLName(@PathVariable String lName, @Valid @RequestBody User user) throws UserNotFoundException {


        User checkUser= userServiceImpl.updateLName(lName, user);

        if (checkUser == null) {
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }

        return checkUser;


    }


    @PutMapping("/update/Email/{email}")
    User updateEmail(@PathVariable String email, @Valid @RequestBody User user) throws UserNotFoundException {



        User checkUser= userServiceImpl.updateEmail(email, user);

        if (checkUser == null) {
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }

        return checkUser;

    }


    @PutMapping("/update/Password/{password}")
    User updatePassword(@PathVariable String password, @Valid @RequestBody User user) throws UserNotFoundException {

        User checkUser= userServiceImpl.updatePassword(password, user);

        if (checkUser == null) {
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }

        return checkUser;
    }


}
