package com.collab.g5.demo.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingsController {
    @Autowired
    BookingService bookingService = new BookingServiceImpl();

    @GetMapping("/booking")
    public List<Bookings> getBookings() {
        return bookingService.getAllBookings();
    }
}
