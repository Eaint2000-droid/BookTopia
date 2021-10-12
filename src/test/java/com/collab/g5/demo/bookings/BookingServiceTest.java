package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserRole;
import com.collab.g5.demo.users.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private BookingsRepository bookingsRepository;
    @InjectMocks
    private BookingServiceImpl bookingService;
    @InjectMocks
    private UserServiceImpl userService;
    LocalDate ldObj = LocalDate.now();

    @Test
    void addBookings_NewTitle_ReturnSavedBookings() {
        //Arrange
        User user = new User("jyphee.2020@scis.smu.edu.sg", "Jing", "Yuan", "Password@1234",
                UserRole.HR, null);
        Bookings bookings = new Bookings(10, user, ldObj, "Approved", new ArrayList<BookingVetting>());

        List<Bookings> sameBookings = new ArrayList<>();
        when(bookingsRepository.save(bookings)).thenReturn(bookings);
        when(bookingsRepository.findAll()).thenReturn(sameBookings);

        //act
        Bookings savedBookings = bookingService.save(bookings);

        //assert
        assertNotNull(savedBookings);
        verify(bookingsRepository).findAll();
        verify(bookingsRepository).save(bookings);
    }

    @Test
    void addBookings_SameBookings_ReturnNull() {
        //Arrange
        User user = new User("jyphee.2020@scis.smu.edu.sg", "Jing", "Yuan", "Password@1234", UserRole.HR, null);
        Bookings bookings = new Bookings(1, user, ldObj, "Approved", new ArrayList<BookingVetting>());

        User user2 = new User("jyphee.2020@scis.smu.edu.sg", "Jing", "Yuan", "Password@1234", UserRole.HR, null);
        Bookings bookings2 = new Bookings(1, user2, ldObj, "Approved", new ArrayList<BookingVetting>());

        List<Bookings> sameBookings = new ArrayList<>();
        sameBookings.add(bookings);

        when(bookingsRepository.findAll()).thenReturn(sameBookings);

        //act
        Bookings secondBooking = bookingService.save(bookings);

        //assert
        assertNull(secondBooking);
        verify(bookingsRepository).findAll();
    }
}


