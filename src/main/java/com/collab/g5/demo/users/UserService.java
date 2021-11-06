package com.collab.g5.demo.users;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    @Query("select u from User u")
    User getUserByEmail(String Email);

    User save(User newUser);

    void delete(User user);

    /**
     * Change method's signature: do not return a value for delete operation
     * @param userEmail
     */
    void deleteById(String userEmail);
}
