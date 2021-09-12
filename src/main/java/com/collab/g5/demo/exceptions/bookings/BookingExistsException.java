package com.collab.g5.demo.exceptions.bookings;

import com.collab.g5.demo.bookings.Bookings;

public class BookingExistsException extends RuntimeException{
    public BookingExistsException(Bookings b) {
        super("Booking " + b.toString() + " exists");

    }
}
