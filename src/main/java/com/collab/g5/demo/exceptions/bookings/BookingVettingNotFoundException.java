package com.collab.g5.demo.exceptions.bookings;

public class BookingVettingNotFoundException extends RuntimeException {
    public BookingVettingNotFoundException(){
        super("NotFound");
    }
}
