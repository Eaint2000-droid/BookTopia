package com.collab.g5.demo.bookings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {
    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @MockBean
    private BookingsRepository bookingsRepository;

    @Test
    void testBookingExists() {
        when(this.bookingsRepository.existsById((Integer) any())).thenReturn(true);
        assertTrue(this.bookingServiceImpl.bookingExists(1));
        verify(this.bookingsRepository).existsById((Integer) any());
        assertTrue(this.bookingServiceImpl.getAllBookings().isEmpty());
    }

    @Test
    void testBookingExists2() {
        when(this.bookingsRepository.existsById((Integer) any())).thenReturn(false);
        assertFalse(this.bookingServiceImpl.bookingExists(1));
        verify(this.bookingsRepository).existsById((Integer) any());
        assertTrue(this.bookingServiceImpl.getAllBookings().isEmpty());
    }
}

