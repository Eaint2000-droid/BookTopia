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

@ContextConfiguration(classes = {BookingVetServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingVetServiceImplTest {
    @MockBean
    private BookingVetRepository bookingVetRepository;

    @Autowired
    private BookingVetServiceImpl bookingVetServiceImpl;

    @Test
    void testBookingVettingExists() {
        when(this.bookingVetRepository.existsById((BookingVettingKey) any())).thenReturn(true);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);
        assertTrue(this.bookingVetServiceImpl.bookingVettingExists(bookingVettingKey));
        verify(this.bookingVetRepository).existsById((BookingVettingKey) any());
        assertTrue(this.bookingVetServiceImpl.getAllBookingVetting().isEmpty());
    }

    @Test
    void testBookingVettingExists2() {
        when(this.bookingVetRepository.existsById((BookingVettingKey) any())).thenReturn(false);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);
        assertFalse(this.bookingVetServiceImpl.bookingVettingExists(bookingVettingKey));
        verify(this.bookingVetRepository).existsById((BookingVettingKey) any());
        assertTrue(this.bookingVetServiceImpl.getAllBookingVetting().isEmpty());
    }
}

