package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookingVetting.BookingVettingExistsException;
import com.collab.g5.demo.users.UserService;
import com.collab.g5.demo.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bVetting")
public class BookingVetController {

    private BookingVetServiceImpl bookingVetServiceImpl;
    private BookingServiceImpl bookingServiceImpl;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public BookingVetController(BookingVetServiceImpl bookingVetServiceImpl, BookingServiceImpl bookingServiceImpl, UserServiceImpl userServiceImpl) {
        this.bookingVetServiceImpl = bookingVetServiceImpl;
        this.bookingServiceImpl = bookingServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/hr")
    public List<BookingVetting> getBookingVetting() {
        return bookingVetServiceImpl.getAllBookingVetting();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hr/get/{id}")
    public BookingVetting getBookingVettingById(@RequestBody BookingVettingKey id) {
        return bookingVetServiceImpl.getById(id);
    }

    //this one might not need to be included? How does this method works?
    @PostMapping("/hr/add/{bookingVet}")
    public BookingVetting addBookingVetting(@RequestBody BookingVetting bkVet) {
        //What i take in is only the PK of Booking and User. I need to get the Object before i can save the BookingVetting object.
        BookingVettingKey pk1 = bkVet.getBookingVettingKey();
        bkVet.setBookingVettingKey(pk1);
        bkVet.setBooking(bookingServiceImpl.getBookingsById(pk1.getBid()));
        bkVet.setUser(userServiceImpl.getUserByEmail(pk1.getEmail()));
        List<BookingVetting> bkList = bookingVetServiceImpl.getAllBookingVetting();
        for (BookingVetting i : bkList) {
            if (i.equals(bkVet)) {
                throw new BookingVettingExistsException();
            }
        }
        return bookingVetServiceImpl.save(bkVet);
    }

    @DeleteMapping("/hr/del/{bookingVet}")
    public void delBookingVetting(@RequestBody BookingVetting bkVet) {
        bookingVetServiceImpl.delete(bkVet);
    }

    @PutMapping("/hr/updateBooking/{bid}/{email}")
    public BookingVetting updateBookingVetting(@PathVariable int bid, @PathVariable String email, @RequestBody BookingVetting newBookingVetting) {
        BookingVettingKey bVid = new BookingVettingKey(bid, email);
        BookingVetting bookingVetting = bookingVetServiceImpl.updateBookings(bVid, newBookingVetting);
        if (bookingVetting == null) throw new BookingVettingExistsException();
        return bookingVetting;
    }


}
