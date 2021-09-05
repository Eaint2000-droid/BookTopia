package com.collab.g5.demo.users;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByEmail(String Email);

    void delete(User user);

    boolean containsUser(String userEmail);

    User save(User newUser);
}
