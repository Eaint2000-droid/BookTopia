package com.collab.g5.demo.users;

import com.collab.g5.demo.exceptions.users.EmailExistsException;
import com.collab.g5.demo.security.WebSecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Autowired
    private UserRepository userRepository;


    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private WebSecurityConfig webSecurityConfig;

    public void addNewUser(User user) {
        String encodedPassword = webSecurityConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String Email) {

        Optional<User> optionalUser= userRepository.findByEmail(Email);
        if(optionalUser.isEmpty()){
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

    @Override
    public void deleteById(String userEmail) {
        userRepository.deleteById(userEmail);
    }




}
