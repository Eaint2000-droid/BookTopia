package com.collab.g5.demo.exceptions.bookingVetting;

public class BookingVettingExistsException extends RuntimeException {
    public BookingVettingExistsException() {
        super("Booking Vetting Exists");
    }
}
