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

    @GetMapping("/")
    public List<BookingVetting> getBookingVetting() {
        return bookingVetService.getAllBookingVetting();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{id}")
    public BookingVetting getBookingVettingById(@RequestBody BookingVettingKey id) {
        return bookingVetService.getById(id);
    }

    @PostMapping("/add/{bookingVet}")
    public BookingVetting addBookingVetting(@RequestBody BookingVetting bkVet) {
        if (bookingVetService.getById(bkVet.getBookingVettingKey()) != null) {
            throw new IllegalStateException();
        }
        return bookingVetService.save(bkVet);
    }

    @DeleteMapping("/del/{bookingVet}")
    public void delBookingVetting(@RequestBody BookingVetting bkVet) {
        bookingVetService.delete(bkVet);
    }


}
