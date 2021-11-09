package com.collab.g5.demo.bookings;

import com.collab.g5.demo.companies.CompanyServiceImpl;
import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.users.UserMonthlyQuotaExceeded;
import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import com.collab.g5.demo.exceptions.users.UserNotVaccinatedException;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserRole;
import com.collab.g5.demo.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
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
    /**
     * List all bookings in the system, mainly for the administrator to view who is in the system.
     *
     * @return list of all bookings
     */
    @GetMapping("/hr")
    @Transient
    public List<Bookings> getBookings() {
        return bookingServiceImpl.getAllBookings();
    }

    /**
     * Returns a list of bookings that is specific to the user with the email specified in the argument.
     *
     * @param email
     * @return List of booking
     */
    @GetMapping("/emp/allEmp/{email}/")
    public List<Bookings> getAllForEmp(@PathVariable String email) {
        System.out.println("Get all my bookings " + email);
        return bookingServiceImpl.getAllMyBookings(email);
    }

    /**
     * Returns a list of all past bookings that is specific to the user with email in argument
     *
     * @param email
     * @return list of all past bookings
     */
    @GetMapping("/emp/past/{email}/")
    public List<Bookings> getAllMyPastBookings(@PathVariable String email) {
        System.out.println("Get all past bookings " + email);
        User u = userServiceImpl.getUserByEmail(email);
        return bookingServiceImpl.getAllMyPastBookings(u);
    }

    /**
     * Returns a list of all upcoming bookings that is specific to the user specified in the argument
     *
     * @param email
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
     *
     * @param email
     * @return booking with the given email
     */
    @GetMapping("/emp/{email}/")
    public int getBookingsCountByEmail(@PathVariable String email) throws BookingNotFoundException {
        System.out.println("BID is " + email);
        return bookingServiceImpl.getBookingsCountByEmail(email);
    }


    /**
     * Returns a list of Bookings that is specific to that particular user with <code>email</code>
     *
     * @param email
     * @return
     * @throws UserNotFoundException
     */
    @GetMapping("/UserBookings/{email}")
    public ArrayList<Bookings> getBookingByUser(@PathVariable String email) throws UserNotFoundException {

        ArrayList<Bookings> toReturn = bookingServiceImpl.getBookingByUser(email);
        if (toReturn == null) {
            throw new UserNotFoundException();
        }
        return toReturn;
    }

    /**
     * Add a new booking with argument in <code>newBooking</code>
     * Stopping Conditions
     * 1) User is not vaccinated
     * 2) User monthly quota exceeded.
     * 3) Company daily limit exceeded.
     *
     * @param newBooking
     * @return the newly added booking
     * @throw UserNotVaccinatedException if user is not vaccinated
     * @throw UserMonthlyQuotaExceeded if user exceeds monthly quota
     * @throw BookingExistsException when booking already exists
     * @throw UserNotFoundException when user does not exist.
     */
    @Transactional
    @PostMapping("/emp/")
    public Bookings addBooking(@RequestBody @Valid Bookings newBooking) {
        System.out.println("new Booking is " + newBooking);
        User userResult = newBooking.getUser();
        System.out.println("Returned user : " + userResult);
        if (bookingServiceImpl.checkForDuplicateBookings(newBooking.getUser().getEmail(), newBooking.getBDate()) > 0) {
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
        //limit variable will store the quota of company of that particular date => total quota
        int limit = companyServiceImpl.getCurrentQuota(cid, newBooking.getBDate());
        System.out.println("The daily limit for this company on this day is " + limit);

        System.out.println("Line 95 Company CID is " + cid + " booking date: " + newBooking.getBDate());
        //getCurrentQuota -> getting number of bookings for that particular date
        int getCurrentQuota = bookingServiceImpl.getBookingsCountByDate(cid, newBooking.getBDate());
        System.out.println("This company have " + getCurrentQuota + " number of employees on " + newBooking.getBDate().toString());

        //userCountByMonth -> retrieve number of bookings made by user for that month.
        int userCountByMonth = bookingServiceImpl.getBookingsCountByUserAndMonth(newBooking.getUser().getEmail(), newBooking.getBDate());
        System.out.println("User has " + userCountByMonth + " bookings");
        System.out.println("Company Limit is " + limit);
        if (userCountByMonth >= 10) {
            throw new UserMonthlyQuotaExceeded(userResult);
        }
        //dailyLimitForUser -> get each company user daily limit.
//        Company tempCompany = companyServiceImpl.getCompanyById(cid);
//        int dailyLimitForUser = tempCompany.getQuota();
//        System.out.println("quota is " + dailyLimitForUser);

        boolean vaccineStatus = userServiceImpl.getVaccinatedByEmail(newBooking.getUser().getEmail());
        if (!vaccineStatus) {
            throw new UserNotVaccinatedException(tempUser);
        }

//        if (userCountByMonth >= dailyLimitForUser) {
//            throw new CompanyDailyLimitExceeded();
//        }

        if (limit == getCurrentQuota) {
            System.out.println("Pending");
            newBooking.setStatus("pending");
        } else {
            System.out.println("Confirmed");
            newBooking.setStatus("Confirmed");
        }
        System.out.println(newBooking);
        Bookings b = bookingServiceImpl.save(newBooking);
        b.getUser().setUserRole(UserRole.HR);
        System.out.println("Finalize is " + b);
        return b;
    }

    /**
     * Remove a booking with the DELETE request to "/hr/{id}"
     * If there is pending bookings that day based on the booking ID,
     * it will find the next booking ID that is pending on that day and
     * set the status to be "Confirmed"
     *
     * @param id
     */
    @DeleteMapping("/hr/del/{id}")
    public void deleteBooking(@PathVariable int id) {
        //First i get the userEmail as i need it to
        System.out.println("ID is " + id);
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


        if (beforeDeleteBookingCount >= limit) {
            System.out.println("Maxed out");
            //i need to do the auto approval stage.
            bookingServiceImpl.delete(bookingServiceImpl.getBookingsById(id)); //delete the current booking

            //set the next booking that is not approved.
            //TODO have to implement this once I have set the other part.
            bookingServiceImpl.autoUpdateBookings(cid, bookingsDate);
        } else {
            bookingServiceImpl.delete(bookingServiceImpl.getBookingsById(id));
        }
        System.out.println("Bid is " + id);
    }


    /**
     * If there is no booking with the given "email", throw a BookingNotFoundException
     *
     * @param id         an int value
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
