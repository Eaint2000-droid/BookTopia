package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserRepository;
import com.collab.g5.demo.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/bookings")
public class BookingsController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    //when admin wants to receive all the information
    @GetMapping("/hr/getAll")
    @Transient
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Bookings> getBookings() {
        return bookingService.getAllBookings();
    }

    //when employee want to retrieve their own booking. TODO
    @GetMapping("/emp/getAll/{bid}")
    public Bookings getBookingsById(@PathVariable int bid) {
        if (!bookingService.bookingExists(bid)) {
            throw new BookingNotFoundException(bid);
        }
        return bookingService.getBookingsById(bid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/emp/{newBooking}")
    public Bookings addBooking(@RequestBody Bookings newBooking) {
        /*
         * This one maybe can delete? Cause I was using postman to test it out and i realized that
         * by using postman, it will allow some invalid userEmail to be added into the console
         * and postman will still go through TODO JY*/
        User a = userService.getUserByEmail(newBooking.getUser().getEmail());
        if (a == null) {
            throw new UserNotFoundException();
        }
        return bookingService.save(newBooking);
    }

    @DeleteMapping("/hr/del/{id}")
    public void deleteBooking(@PathVariable int id) {
        Bookings bookings = bookingService.getBookingsById(id);
        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }
        bookingService.delete(getBookingsById(id));
    }

}
