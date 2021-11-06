package com.collab.g5.demo.bookings;

import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.users.User;

import java.util.ArrayList;
import java.util.List;

public interface BookingService {
    //CREATE
    Bookings save(Bookings bookings);

    //READ
    List<Bookings> getAllBookings();

    Bookings getBookingsById(int id);

    //UPDATE
    Bookings updateBookings(int id, Bookings bookings);

    //DELETE
    void delete(Bookings bookings);

    /**
     * Change method's signature: do not return a value for delete operation
     * @param id
     */
    void deleteById(int id);

    boolean bookingExists(int id);

    ArrayList<Bookings> getBookingByUser(String email);
}
