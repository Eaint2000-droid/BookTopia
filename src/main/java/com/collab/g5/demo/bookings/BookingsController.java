package com.collab.g5.demo.bookings;

import com.collab.g5.demo.companies.CompanyServiceImpl;
import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000/")
public class BookingsController {

    private BookingServiceImpl bookingServiceImpl;
    private CompanyServiceImpl companyServiceImpl;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public BookingsController(BookingServiceImpl bookingServiceImpl, UserServiceImpl userServiceImpl, CompanyServiceImpl companyServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.companyServiceImpl = companyServiceImpl;
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

    @GetMapping("/emp/getAllForEmp/{email}/")
    public List<Bookings> getAllForEmp(@PathVariable String email) {
        System.out.println("Get all my bookings " + email);
        return bookingServiceImpl.getAllMyBookings(email);
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

    /**
     * List all upcoming bookings in the system
     * @return list of all upcoming bookings
     */
    @GetMapping("/emp/upcoming/{email}/")
    public List<Bookings> getAllMyUpcomingBookings(@PathVariable String email) {
        System.out.println("get all upcoming bookings");
        User u = userServiceImpl.getUserByEmail(email);
        return bookingServiceImpl.getAllMyUpcomingBookings(u);
    }

    /**
     * Search for booking with the given email
     * If there is no booking with the given "email", throw a BookingNotFoundException
     * @param email
     * @return booking with the given email
     */
    @GetMapping("/emp/{email}/")
    public int getBookingsCountByEmail(@RequestParam String email) throws BookingNotFoundException {
        System.out.println("BID is " + email);
        return bookingServiceImpl.getBookingsCountByEmail(email);
    }

    //TODO have to do error handling so that i check if there is enough slots before i save the booking.
    //TODO when i add a booking in, i have to check 2 things.
    // 1 is if the user have enough booking slots left per month.
    // 2 is if the daily limit for that particular day is full.
    /**
     * Add a new booking with POST request to "/emp"
     * @param newBooking
     * @return the newly added booking
     */
    @ResponseStatus(HttpStatus.CREATED)
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
        //maximum number of users that can go back on this particular date
        System.out.println("Line 86 " + newBooking);
        User tempUser = userServiceImpl.getUserByEmail(newBooking.getUser().getEmail());
        System.out.println("Line 88 " + tempUser.getCompany());
        int cid = tempUser.getCompany().getCid();
        //Company tempCompany = companyServiceImpl.getCompanyById()
        System.out.println("Before going into the method: " + cid + " booking " + newBooking.getBDate());
        int limit = companyServiceImpl.getCurrentQuota(cid, newBooking.getBDate());
        System.out.println("The daily limit for this company on this day is " + limit);
        //getting number of users that is registered into the system on this date.
        System.out.println("Line 95 Company CID is " + cid + " booking date: " + newBooking.getBDate());
        int getCurrentQuota = bookingServiceImpl.getBookingsCountByDate(cid, newBooking.getBDate());
        System.out.println("This company have " + getCurrentQuota + " number of employees on " + newBooking.getBDate().toString());
        if (limit == getCurrentQuota) {
            System.out.println("Pending");
            newBooking.setStatus("pending");
        } else {
            System.out.println("Completed");
            newBooking.setStatus("completed");
        }
        return bookingServiceImpl.save(newBooking);
    }

    /*
     * I need to find the CID of the user who booked the bookings.
     */
    /**
     * Remove a booking with the DELETE request to "/hr/{id}"
     * If there is no booking with the given "id", throw a BookingNotFoundException
     * @param id
     */
    @DeleteMapping("/hr/del/{id}")
    public void deleteBooking(@RequestParam int id) throws BookingNotFoundException {
        //First i get the userEmail as i need it to
        Bookings bookings = bookingServiceImpl.getBookingsById(id);
        LocalDate bookingsDate = bookings.getBDate();
        System.out.println("Booking Date is " + bookingsDate);
        System.out.println("current Booking is " + bookings);
        User tempUser = bookings.getUser();
        System.out.println("current tempUser is " + tempUser);
        int cid = tempUser.getCompany().getCid();
        System.out.println("Cid is " + cid);
        //i need to parse in the date too though, the date of the bookings that im deleting.
        int limit = companyServiceImpl.getCurrentQuota(cid, bookingsDate);
        System.out.println("Limit is " + limit);
        //I have gotten my limit for this particular company.


        //Get the count of the number of bookings for this company in this particular month.
        int beforeDeleteBookingCount = bookingServiceImpl.getBookingsCountByDate(cid, bookingsDate);
        System.out.println("Booking Count is " + beforeDeleteBookingCount);

        if (beforeDeleteBookingCount == limit) {
            //i need to do the auto approval stage.
            bookingServiceImpl.delete(bookingServiceImpl.getBookingsById(id)); //delete the current booking

            //set the next booking that is not approved.
            //TODO have to implement this once I have set the other part.
            bookingServiceImpl.autoUpdateBookings(bookingsDate.getMonthValue());


        } else {
            bookingServiceImpl.delete(bookingServiceImpl.getBookingsById(id));
        }

        System.out.println("Bid is " + id);
        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }


    }


    /**
     * If there is no booking with the given "email", throw a BookingNotFoundException
     * @param id an int value
     * @param newBooking a Booking object containing the new booking info to be updated
     * @return the updated, or newly added booking
     */
    @PutMapping("/hr/{id}")
    public Bookings updateBookings(@PathVariable int id, @RequestBody Bookings newBooking) throws BookingNotFoundException {
        Bookings bookings = bookingServiceImpl.updateBookings(id, newBooking);

        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }
        return bookings;
    }



}
