package com.collab.g5.demo.bookings;

public class BookingVettingNotFoundException extends RuntimeException {
    public BookingVettingNotFoundException(){
        super("NotFound");
    }
}
