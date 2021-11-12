package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookingVetting.BookingVettingExistsException;
import com.collab.g5.demo.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bVetting")
@CrossOrigin
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

    /**
     * List all booking vettings in the system
     * @return list of all booking vettings
     */
    @GetMapping("/hr")
    public List<BookingVetting> getBookingVetting() {
        return bookingVetServiceImpl.getAllBookingVetting();
    }

    /**
     * Search for booking vetting with the given id
     * If there is no booking vetting with the given "id", throw a NewsNotFoundException
     * @param id
     * @return news with the given nid
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hr/{id}")
    public BookingVetting getBookingVettingById(@RequestBody BookingVettingKey id) {
        return bookingVetServiceImpl.getById(id);
    }

    //this one might not need to be included? How does this method works?
    /**
     * Add a new booking vetting with POST request to "/hr/{bookingVet}"
     * @param bkVet
     * @return the newly added booking vetting
     */
    @PostMapping("/hr/{bookingVet}")
    public BookingVetting addBookingVetting(@RequestBody BookingVetting bkVet) throws BookingVettingExistsException {
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

    /**
     * If there is no booking vetting with the given "bid", throw a BookingVettingExistsException
     * @param bid an int value
     * @param email an String value
     * @param newBookingVetting a BookingVetting object containing the new booking vetting info to be updated
     * @return the updated, or newly added booking vetting
     */
    @PutMapping("/hr/{bid}/{email}")
    public BookingVetting updateBookingVetting(@PathVariable int bid, @PathVariable String email, @RequestBody BookingVetting newBookingVetting) throws BookingVettingExistsException {
        BookingVettingKey bVid = new BookingVettingKey(bid, email);
        BookingVetting bookingVetting = bookingVetServiceImpl.updateBookings(bVid, newBookingVetting);
        if (bookingVetting == null) throw new BookingVettingExistsException();
        return bookingVetting;
    }

    /**
     * Remove a booking vetting with the DELETE request to "/hr/{bookingVet}"
     * If there is no news with the given "bkVet", throw a BookingVettingNotFoundException
     * @param bkVet
     */
    @DeleteMapping("/hr/{bookingVet}")
    public void delBookingVetting(@RequestBody BookingVetting bkVet) {
        bookingVetServiceImpl.delete(bkVet);
    }

}
