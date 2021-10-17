package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import com.collab.g5.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    public BookingsController(BookingServiceImpl bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    //when admin wants to receive all the information
    @GetMapping("/hr/getAll")
    @Transient
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Bookings> getBookings() {
        return bookingServiceImpl.getAllBookings();
    }

    //when employee want to retrieve their own booking. TODO
    @GetMapping("/emp/getAll/{bid}")
    public Bookings getBookingsById(@RequestParam int bid) {
        if (!bookingServiceImpl.bookingExists(bid)) {
            throw new BookingNotFoundException(bid);
        }
        return bookingServiceImpl.getBookingsById(bid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/emp/")
    public Bookings addBooking(@RequestBody Bookings newBooking) {
        User a = newBooking.getUser();
        if(bookingServiceImpl.bookingExists(newBooking.getBid())){
            throw new BookingExistsException(newBooking);
        }
        if (a == null) {
            throw new UserNotFoundException();
        }
        return bookingServiceImpl.save(newBooking);
    }

    @DeleteMapping("/hr/del/{id}")
    public void deleteBooking(@RequestParam int id) {
        Bookings bookings = bookingServiceImpl.getBookingsById(id);
        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }

        bookingServiceImpl.delete(bookingServiceImpl.getBookingsById(id));
    }

    @PutMapping("/hr/update/{id}")
    public Bookings updateBookings(@PathVariable int id, @RequestBody Bookings newBooking){
        Bookings bookings = bookingServiceImpl.updateBookings(id, newBooking);

        if(bookings == null){
            throw new BookingNotFoundException(id);
        }
        return bookings;
    }

}
