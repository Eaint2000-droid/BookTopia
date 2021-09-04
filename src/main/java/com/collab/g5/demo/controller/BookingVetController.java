package com.collab.g5.demo.controller;

import com.collab.g5.demo.entity.BookingVetting;
import com.collab.g5.demo.service.BookingVetImpl;
import com.collab.g5.demo.service.BookingVetService;
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
