package com.collab.g5.demo.users;

import com.collab.g5.demo.email.Mail;
import com.collab.g5.demo.email.MailService;
import com.collab.g5.demo.security.WebSecurityConfig;
import lombok.AllArgsConstructor;
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


    @Autowired
    public UserServiceImpl(UserRepository userRepository, MailService mailService, WebSecurityConfig webSecurityConfig) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.webSecurityConfig = webSecurityConfig;

    }

    @Override
    public void forgetPassword(String email) {
        User user = userRepository.getById(email);
        Mail mail = new Mail();
        mail.setMailFrom("weloveis211@gmail.com");
        String userEmail = user.getEmail();
        System.out.println("User Email in Service Implementation is " + userEmail);
        System.out.println("user Object is " + user);
        mail.setMailTo(userEmail);
        mail.setMailSubject("Welcome to Company X ," + user.getEmail());
        mail.setMailContent("Dear " + user.getName() + ",\n\n" +
                "It appears that you have trouble logging in.\n" +

                "Please use the link below To reset your password.\n" +

                "Warm Regards, \n"
                + "CS203G5 Team"

        );
        mailService.sendEmail(mail);
    }


    public void addNewUser(User user) {
        Mail mail = new Mail();
        mail.setMailFrom("weloveis211@gmail.com");
        String userEmail = user.getEmail();
        System.out.println("User Email in Service Implementation is " + userEmail);
        System.out.println("user Object is " + user);
        mail.setMailTo(userEmail);
        mail.setMailSubject("Welcome to Company X ," + user.getEmail());
        mail.setMailContent("Dear " + user.getName() + "\n\n" +
                "We are excited to get you on board our application.\n " +
                "\n" +
                "You may login with these details:\n" +
                "Username - " + userEmail + "\n" +
                "Password - password1. \n " +
                "\n" +
                "Please change your password in profile settings upon entering the application. \n" +
                "Using our application you will be able to check-in and book slots with greater convenience.\n" +
                "In addition, you may choose to keep up to date with the latest news and covid regulations\n" +
                "Wishing you a great experience!"
                + "\nWarm Regards, \n"
                + "CS203G5 Team"

        );
        mailService.sendEmail(mail);

        //setting default password to be password1
        String encodedPassword = webSecurityConfig.passwordEncoder().encode("password1");
        user.setVaccinated(false);
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

    @Override
    public User setForgetPassword(String email, String password) {

        User user = getUserByEmail(email);

        if (user == null) {
            return null;
        }

        String encodedPassword = webSecurityConfig.passwordEncoder().encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return user;


    }

}
