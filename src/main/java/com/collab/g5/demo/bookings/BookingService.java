package com.collab.g5.demo.bookings;

import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.users.User;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Bookings> getAllBookings();
    Bookings getBookingsById(int id);
    void delete(Bookings bookings);
    boolean bookingExists(int id);
    Bookings save(Bookings bookings);
}
