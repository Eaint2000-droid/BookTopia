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

    User updatePassword(String password, User user);

    User updateFname(String fname, User user);

    User updateLName(String lName, User user);

    User updateEmail(String email, User user);


//    boolean containsUser(String userEmail);
}
