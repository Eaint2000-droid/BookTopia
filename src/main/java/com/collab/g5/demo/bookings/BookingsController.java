package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000/")
public class BookingsController {

    private BookingServiceImpl bookingServiceImpl;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public BookingsController(BookingServiceImpl bookingServiceImpl , UserServiceImpl userServiceImpl)
    {
        this.bookingServiceImpl = bookingServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * List all bookings in the system
     * @return list of all bookings
     */
    @GetMapping("/hr")
    @Transient
    public List<Bookings> getBookings() {
        System.out.println("Get all method");
        return bookingServiceImpl.getAllBookings();
    }

    /**
     * List all past bookings in the system
     * @return list of all past bookings
     */
    @GetMapping("/emp/past/{email}/")
    public List<Bookings> getAllMyPastBookings(@PathVariable String email) {
        System.out.println("Get all past bookings " + email);
        User u = userServiceImpl.getUserByEmail(email);
        return bookingServiceImpl.getAllMyPastBookings(u);
    }

    @GetMapping("/emp/upcoming/{email}/")
    public List<Bookings> getAllMyUpcomingBookings(@PathVariable String email) {
        System.out.println("get all upcoming bookings");
        User u = userServiceImpl.getUserByEmail(email);
        return bookingServiceImpl.getAllMyUpcomingBookings(u);
    }

    @GetMapping("/emp/{email}/")
    public int getBookingsCountByEmail(@RequestParam String email) throws BookingNotFoundException {
        System.out.println("BID is " + email);
        return bookingServiceImpl.getBookingsCountByEmail(email);
    }

    @PostMapping("/emp")
    public Bookings addBooking(@RequestBody Bookings newBooking) throws BookingExistsException, UserNotFoundException {
        User userResult = newBooking.getUser();
        System.out.println("Returned user : " + userResult);
        if (bookingServiceImpl.bookingExists(newBooking.getBid())) {
            throw new BookingExistsException(newBooking);
        }
        if (userResult == null) {
            throw new UserNotFoundException();
        }
        return bookingServiceImpl.save(newBooking);
    }

    @DeleteMapping("/hr/{id}")
    public void deleteBooking(@RequestParam int id) throws BookingNotFoundException {
        System.out.println("Bid is " + id);
        Bookings bookings = bookingServiceImpl.getBookingsById(id);
        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }

        bookingServiceImpl.delete(bookingServiceImpl.getBookingsById(id));
    }

    @PutMapping("/hr/{id}")
    public Bookings updateBookings(@PathVariable int id, @RequestBody Bookings newBooking) throws BookingNotFoundException {
        Bookings bookings = bookingServiceImpl.updateBookings(id, newBooking);

        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }
        return bookings;
    }

}
