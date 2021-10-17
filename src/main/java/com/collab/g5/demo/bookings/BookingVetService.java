package com.collab.g5.demo.bookings;

import java.util.List;

public interface BookingVetService {
    //CREATE
    BookingVetting save(BookingVetting bookingVetting);

    //READ
    List<BookingVetting> getAllBookingVetting();

    BookingVetting getById(BookingVettingKey id);

    //UPDATE
    BookingVetting updateBookings(BookingVettingKey id, BookingVetting bookings);

    //DELETE
    void delete(BookingVetting bookingVetting);

    void deleteById(BookingVettingKey id);

    boolean bookingVettingExists(BookingVettingKey id);
}
