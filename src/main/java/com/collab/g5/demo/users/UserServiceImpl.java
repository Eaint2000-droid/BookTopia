package com.collab.g5.demo.users;

import com.collab.g5.demo.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //default from UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void addNewUser(User user) {
        //checks if email exist
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        //if yes throw error
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        //encrypting password
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder()
                .encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String Email) {
        return userRepository.getById(Email);
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);

    }

    @Override
    public void deleteById(String userEmail) {
        userRepository.deleteById(userEmail);
    }

    @Override
    public boolean containsUser(String userEmail) {
        return userRepository.existsById(userEmail);
    }


}
