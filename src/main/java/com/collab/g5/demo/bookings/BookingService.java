package com.collab.g5.demo.bookings;

import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.users.User;

import java.time.LocalDate;
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

    void deleteById(int id);

    boolean bookingExists(int id);

    ArrayList<Bookings> getBookingByUser(String email);

    List<Bookings> getAllMyBookings(String email);

    int getBookingsCountByEmail(String email);

    List<Bookings> getAllMyUpcomingBookings(User u);

    void autoUpdateBookings(int cid, LocalDate date);

    int getBookingsCountByUserAndMonth(String email, LocalDate date);

    int getBookingsCountByDate(int cid, LocalDate date);

    List<Bookings> getAllMyPastBookings(User u);
}
