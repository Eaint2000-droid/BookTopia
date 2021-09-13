package com.collab.g5.demo.bookings;

import com.collab.g5.demo.bookings.BookingVetting;

import java.util.List;

public interface BookingVetService {
    //CREATE
    BookingVetting save(BookingVetting bookingVetting);

    //READ
    List<BookingVetting> getAllBookingVetting();

    BookingVetting getBookingVettingById(BookingVettingKey id);

    //UPDATE
    BookingVetting updateBookings(int id, BookingVetting bookings);

    //DELETE
    void delete(BookingVetting bookingVetting);

    void deleteById(BookingVettingKey id);

    boolean bookingVettingExists(BookingVettingKey id);
}
