package com.collab.g5.demo.users;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByEmail(String Email);

    User save(User newUser);

    void delete(User user);

    void deleteById(String userEmail);

    boolean containsUser(String userEmail);
}
