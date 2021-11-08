package com.collab.g5.demo.users;

import com.collab.g5.demo.email.Mail;
import com.collab.g5.demo.email.MailService;
import com.collab.g5.demo.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private UserRepository userRepository;
    private MailService mailService;
    private WebSecurityConfig webSecurityConfig;

//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MailService mailService, WebSecurityConfig webSecurityConfig) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.webSecurityConfig = webSecurityConfig;

    }

    public void addNewUser(User user) {
        Mail mail = new Mail();
        mail.setMailFrom("weloveis211@gmail.com");
        String userEmail = user.getEmail();
        System.out.println("User Email in Service Implementation is " + userEmail);
        System.out.println("user Object is " + user);
        mail.setMailTo(userEmail);
        mail.setMailSubject("Welcome to Company X " + user.getEmail());
        mail.setMailContent("Your passwword is password1. \n Please remember to change your password");
        mailService.sendEmail(mail);

        //setting default password to be password1
        String encodedPassword = webSecurityConfig.passwordEncoder().encode("password1");
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String Email) {

        Optional<User> optionalUser = userRepository.findByEmail(Email);
        if (optionalUser.isEmpty()) {
            return null;
        }

        return optionalUser.get();
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);

    }

    /**
     * Remove a user with the given userEmail
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a user will also remove all its associated information
     */
    @Override
    public void deleteById(String userEmail) {
        userRepository.deleteById(userEmail);
    }

    @Override
    public boolean getVaccinatedByEmail(String email) {
        return userRepository.getVaccinatedByEmail(email);
    }

    @Override
    public User updatePassword(String password, User user) {
        User userExist = userRepository.getById(user.getEmail());
        if (userExist == null) {
            // throw an exception
            return null;
        }
        String encodedPassword = webSecurityConfig.passwordEncoder().encode(password);
        userExist.setPassword(encodedPassword);
        userRepository.save(userExist);
        return userExist;
    }

    @Override
    public User updateFname(String fName, User user) {
        User userExist = userRepository.getById(user.getEmail());
        if (userExist == null) {
            // throw an exception
            return null;
        }

        userExist.setFname(fName);
        userRepository.save(userExist);
        return userExist;
    }

    @Override
    public User updateLName(String lName, User user) {
        User userExist = userRepository.getById(user.getEmail());
        if (userExist == null) {
            // throw an exception
            return null;
        }

        userExist.setLname(lName);
        userRepository.save(userExist);
        return userExist;
    }

    @Override
    public User updateEmail(String email, User user) {
        User userExist = userRepository.getById(user.getEmail());
        if (userExist == null) {
            // throw an exception
            return null;
        }

        userExist.setEmail(email);
        userRepository.save(userExist);
        return userExist;
    }

    @Override
    public User updateVaccination(boolean vaccination, User user) {
        User userExist = userRepository.getById(user.getEmail());
        if (userExist == null) {
            // throw an exception
            return null;
        }

        userExist.setVaccinated(vaccination);
        userRepository.save(userExist);
        return userExist;
    }


}
