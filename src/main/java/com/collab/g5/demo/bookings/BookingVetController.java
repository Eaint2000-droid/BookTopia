package com.collab.g5.demo.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingVetController {
    @Autowired
    BookingVetService bookingVetService = new BookingVetImpl();

    @GetMapping("/bookingsAdmin")
    public List<BookingVetting> getBookingsAdmin(){
        return bookingVetService.getAllBookings();
    }
}
