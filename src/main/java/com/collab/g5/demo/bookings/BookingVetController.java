package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookingVetting.BookingVettingNotFoundException;
import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bVetting")
public class BookingVetController {

    @Autowired
    private BookingVetService bookingVetService;

    @GetMapping("/hr")
    public List<BookingVetting> getBookingVetting() {
        return bookingVetService.getAllBookingVetting();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hr/get/{id}")
    public BookingVetting getBookingVettingById(@RequestBody BookingVettingKey id) {
        return bookingVetService.getById(id);
    }

    //this one might not need to be included? How does this method works?
    @PostMapping("/hr/add/{bookingVet}")
    public BookingVetting addBookingVetting(@RequestBody BookingVetting bkVet) {
        if (bookingVetService.getById(bkVet.getBookingVettingKey()) != null) {
            throw new IllegalStateException();
        }
        return bookingVetService.save(bkVet);
    }

    @DeleteMapping("/hr/del/{bookingVet}")
    public void delBookingVetting(@RequestBody BookingVetting bkVet) {
        bookingVetService.delete(bkVet);
    }


}
