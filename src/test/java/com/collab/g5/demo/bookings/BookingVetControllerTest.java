package com.collab.g5.demo.bookings;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.collab.g5.demo.security.PasswordEncoder;
import com.collab.g5.demo.users.UserRepository;
import com.collab.g5.demo.users.UserServiceImpl;
import org.junit.jupiter.api.Test;

class BookingVetControllerTest {
    @Test
    void testConstructor() {
        BookingVetServiceImpl bookingVetServiceImpl = new BookingVetServiceImpl();
        BookingServiceImpl bookingServiceImpl = new BookingServiceImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, new PasswordEncoder());

        new BookingVetController(bookingVetServiceImpl, bookingServiceImpl, userServiceImpl);

        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }
}

