package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import com.collab.g5.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000/")
public class BookingsController {

    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    public BookingsController(BookingServiceImpl bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    //when admin wants to receive all the information
    @GetMapping("/hr/getAll")
    @Transient
    public List<Bookings> getBookings() {
        return bookingServiceImpl.getAllBookings();
    }

//    //when user what to get their booking info
//    @GetMapping("/emp/getAll")
//    @Transient
//    @CrossOrigin(origins = "http://localhost:3000")
//    public List<Bookings> getAllMyBookings() {
//        return bookingServiceImpl.getAllMyBookings();
//    }

    //user retrieve all their past booking records
    @GetMapping("/emp/getAllMyPast")
    @Transient
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Bookings> getAllMyPastBookings() {
        return bookingServiceImpl.getAllMyPastBookings();
    }

    @GetMapping("/emp/getAllMyUpcoming")
    @Transient
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Bookings> getAllMyUpcomingBookings() {
        return bookingServiceImpl.getAllMyUpcomingBookings();
    }

    @GetMapping("/emp/getAll/{bid}")
    public Bookings getBookingsById(@RequestParam int bid) throws BookingNotFoundException {
        if (!bookingServiceImpl.bookingExists(bid)) {
            throw new BookingNotFoundException(bid);
        }
        return bookingServiceImpl.getBookingsById(bid);
    }
    @GetMapping("/getUserBookings/{email}")
    public ArrayList<Bookings> getBookingByUser(@PathVariable String email) throws UserNotFoundException{

        ArrayList<Bookings> toReturn= bookingServiceImpl.getBookingByUser(email);
        if(toReturn==null){
            throw new UserNotFoundException();
        }
        return toReturn;

    }





    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/emp/")
    public Bookings addBooking(@RequestBody Bookings newBooking) throws BookingExistsException, UserNotFoundException {
        System.out.println("New Booking: " + newBooking);
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

    @DeleteMapping("/hr/del/{id}")
    public void deleteBooking(@RequestParam int id) throws BookingNotFoundException {
        Bookings bookings = bookingServiceImpl.getBookingsById(id);
        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }

        bookingServiceImpl.delete(bookingServiceImpl.getBookingsById(id));
    }

    @PutMapping("/hr/update/{id}")
    public Bookings updateBookings(@PathVariable int id, @RequestBody Bookings newBooking) throws BookingNotFoundException {
        Bookings bookings = bookingServiceImpl.updateBookings(id, newBooking);

        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }
        return bookings;
    }

}
