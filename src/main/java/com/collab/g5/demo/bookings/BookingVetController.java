package com.collab.g5.demo.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingVetController {

    @Autowired
    private BookingVetService bookingVetService;

    @GetMapping("/bookingsAdmin")
    public List<BookingVetting> getBookingsAdmin(){
        ArrayList<String> toReturn= new ArrayList<>();

        if(bookingVetService.getAllBookings().size()==0){
            throw new BookingVettingNotFoundException();
        }

        return bookingVetService.getAllBookings();
    }


}
