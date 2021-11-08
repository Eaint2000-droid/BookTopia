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
    @PostMapping("/hr/new")
    public void newUser(@RequestBody User newUser) throws EmailExistsException{
        System.out.println("New User is " + newUser);
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
    @GetMapping("/emp/email/{email}")
    public User getUserByEmail(@PathVariable String email) throws UsernameNotFoundException{
        System.out.println(email);
        if(userServiceImpl.getUserByEmail(email)==null){

            throw new UsernameNotFoundException("Email not found " + email);
        }

        System.out.println(userServiceImpl.getUserByEmail(email));
        return userServiceImpl.getUserByEmail(email);
    }

    /**
     * Search for user vax status with the given email
     * If there is no user with the given "email", throw a UsernameNotFoundException
     * @param email
     * @return user with the given email
     */
    @GetMapping("/emp/emailVax/{email}")
    public Boolean getUserVaxByEmail(@PathVariable String email) throws UsernameNotFoundException{
        if(userServiceImpl.getUserByEmail(email)==null){
            throw new UsernameNotFoundException("Email not found " + email);
        }
        return userServiceImpl.getUserByEmail(email).getVaccinated();
    }

    /**
     * Remove a user with the DELETE request to "/hr/{email}"
     * If there is no news with the given "nid", throw a UserNotFoundException
     * @param email
     */



    @DeleteMapping("/hr/email/{email}")
    void deleteUser(@PathVariable String email) throws UserNotFoundException{
        User user = userServiceImpl.getUserByEmail(email);
        if (user == null) {
            // throw an exception
            throw new UserNotFoundException(email);
        }
        userServiceImpl.delete(user);
    }

    @PutMapping("emp/fname/{fname}")
    User updateFname(@PathVariable String fname, @Valid @RequestBody User user) throws UserNotFoundException {


        User checkUser= userServiceImpl.updateFname(fname, user);

        if (checkUser == null) {
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }

        return checkUser;

    }

    @PutMapping("emp/lname/{lname}")
    User updateLName(@PathVariable String lname, @Valid @RequestBody User user) throws UserNotFoundException {


        User checkUser= userServiceImpl.updateLName(lname, user);

        if (checkUser == null) {
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }

        return checkUser;


    }


    @PutMapping("emp/new/email/{email}")
    User updateEmail(@PathVariable String email, @Valid @RequestBody User user) throws UserNotFoundException {



        User checkUser= userServiceImpl.updateEmail(email, user);

        if (checkUser == null) {
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }

        return checkUser;

    }


    @PutMapping("emp/new/Password/{password}")
    User updatePassword(@PathVariable String password, @Valid @RequestBody User user) throws UserNotFoundException {

        User checkUser= userServiceImpl.updatePassword(password, user);

        if (checkUser == null) {
            System.out.println("User is not found");
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }
        System.out.println("user Found!") ;

        return checkUser;
    }


    @PutMapping("hr/new/vaccination/{vaccination}")
    User updateVaccination(@PathVariable boolean vaccination, @Valid @RequestBody User user) throws UserNotFoundException {

        System.out.println("Update Password");
        System.out.println(user);
        User checkUser= userServiceImpl.updateVaccination(vaccination, user);

        if (checkUser == null) {
            System.out.println("User is not found");
            // throw an exception
            throw new UserNotFoundException(user.getEmail());
        }
        System.out.println("user Found!") ;

        return checkUser;
    }


}
