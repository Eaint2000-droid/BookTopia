package com.collab.g5.demo.users;

import com.collab.g5.demo.exceptions.users.EmailExistsException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    /**
     * List all users in the system
     * @return list of all users
     */
    @GetMapping("/hr")
    public List<User> getUsers() throws UserNotFoundException{
        List<User> toReturn = userServiceImpl.getAllUsers();
        if (toReturn.size() == 0) {
            throw new UserNotFoundException();
        }
        return toReturn;
    }

    /**
     * Add a new user with POST request to "/hr"
     * @param newUser
     * @return the newly added user
     */
    @PostMapping("/hr")
    public void newUser(@RequestBody User newUser) throws EmailExistsException{
        try {
            User userExists = getUserByEmail(newUser.getEmail());
            throw new EmailExistsException("email already taken");
        }catch(UsernameNotFoundException e){
            userServiceImpl.addNewUser(newUser);
        }

    }

    /**
     * Search for user with the given email
     * If there is no user with the given "email", throw a UsernameNotFoundException
     * @param email
     * @return user with the given email
     */
    @GetMapping("/hr/{email}")
    public User getUserByEmail(@PathVariable String email) throws UsernameNotFoundException{

        if(userServiceImpl.getUserByEmail(email)==null){
            throw new UsernameNotFoundException("Email not found " + email);
        }
        return userServiceImpl.getUserByEmail(email);
    }

    /**
     * Remove a user with the DELETE request to "/hr/{email}"
     * If there is no news with the given "nid", throw a UserNotFoundException
     * @param email
     */
    @DeleteMapping("/hr/{email}")
    void deleteUser(@PathVariable String email) throws UserNotFoundException{
        User user = userServiceImpl.getUserByEmail(email);
        if (user == null) {
            // throw an exception
            throw new UserNotFoundException(email);
        }
        userServiceImpl.delete(user);
    }

}
