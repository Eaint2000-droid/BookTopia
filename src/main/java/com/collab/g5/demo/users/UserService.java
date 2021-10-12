package com.collab.g5.demo.users;

import com.collab.g5.demo.companies.Company;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    @Query("select u from User u")
    User getUserByEmail(String Email);

    User save(User newUser);

    void delete(User user);

    void deleteById(String userEmail);

//    boolean containsUser(String userEmail);
}
