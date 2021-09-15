package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Bookings> getBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/get/{bid}")
    public Bookings getBookingsById(@PathVariable int bid){
        if(!bookingService.bookingExists(bid)){
            throw new BookingNotFoundException(bid);
        }
        return bookingService.getBookingsById(bid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{newBooking}")
    public Bookings addBooking(@RequestBody Bookings newBooking){
        return bookingService.save(newBooking);
    }

    @DeleteMapping("/del/{email}")
    public void deleteBooking(@PathVariable int id){
        Bookings bookings = bookingService.getBookingsById(id);
        if(bookings == null){
            throw new BookingNotFoundException(id);
        }
        bookingService.delete(getBookingsById(id));
    }

}
