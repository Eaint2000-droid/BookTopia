package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import com.collab.g5.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private BookingService bookingService;

    @Autowired
    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //when admin wants to receive all the information
    @GetMapping("/hr/getAll")
    @Transient
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Bookings> getBookings() {
        return bookingService.getAllBookings();
    }

    //when employee want to retrieve their own booking. TODO
    @GetMapping("/emp/getAll/{bid}")
    public Bookings getBookingsById(@RequestParam int bid) {
        if (!bookingService.bookingExists(bid)) {
            throw new BookingNotFoundException(bid);
        }
        return bookingService.getBookingsById(bid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/emp/")
    public Bookings addBooking(@RequestBody Bookings newBooking) {
        /*
         * This one maybe can delete? Cause I was using postman to test it out and i realized that
         * by using postman, it will allow some invalid userEmail to be added into the console
         * and postman will still go through TODO JY*/
        User a = newBooking.getUser();
        if (a == null) {
            throw new UserNotFoundException();
        }
        return bookingService.save(newBooking);
    }

    @DeleteMapping("/hr/del/{id}")
    public void deleteBooking(@RequestParam int id) {
        Bookings bookings = bookingService.getBookingsById(id);
        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }
        bookingService.delete(bookingService.getBookingsById(id));
    }

}
