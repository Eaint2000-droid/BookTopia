package com.collab.g5.demo.users;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.collab.g5.demo.security.PasswordEncoder;
import org.junit.jupiter.api.Test;

class UserControllerTest {
    @Test
    void testConstructor() {
        UserRepository userRepository = mock(UserRepository.class);
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, new PasswordEncoder());

        new UserController(userServiceImpl);
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }
}

