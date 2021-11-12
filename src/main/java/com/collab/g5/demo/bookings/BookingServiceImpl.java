package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {


    private BookingsRepository bookingsRepository;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public BookingServiceImpl(BookingsRepository bookingsRepository, UserServiceImpl userServiceImpl) {
        this.bookingsRepository = bookingsRepository;
        this.userServiceImpl = userServiceImpl;

    }

    //for hr
    @Override
    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }

    @Override
    public List<Bookings> getAllMyBookings(String email) {
        return bookingsRepository.findBookingsByEmail(email);
    }

    @Override
    public List<Bookings> getAllMyPastBookings(User u) {
        System.out.println("getAllMyPastBookings: " + u);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Local Time is " + now);
        List<Bookings> bookingsList = bookingsRepository.findAllByUser(u);
        System.out.println(bookingsList.size());
        Iterator<Bookings> iterator = bookingsList.iterator();
        while (iterator.hasNext()) {
            Bookings b = iterator.next();
            System.out.println(b.getBDate());
            if (!b.getBDate().isBefore(ChronoLocalDate.from(now))) {
                iterator.remove();
            }
        }
        System.out.println(bookingsList.size());

        return bookingsList;
    }

    // This is to get the count of bookings that that particular month has already.
    @Override
    public int getBookingsCountByDate(int cid, LocalDate date) {
        System.out.println(cid + " " + date);
        System.out.println("Local Date is " + date.getMonthValue());
        return bookingsRepository.getBookingsCountByDate(cid, date);
    }

    @Override
    public int getBookingsCountByUserAndMonth(String email, LocalDate date) {
        int month = date.getMonthValue();
        return bookingsRepository.getBookingsCountByUserAndMonth(email, month);
    }

    //TODO
    @Override
    public void autoUpdateBookings(int cid, LocalDate date) {
        //remove all the users that does not belong to the same company.
        List<User> userList = userServiceImpl.getAllUsers();
        System.out.println("Auto update booking :" + userList);
//        for (User user : userList) {
//            user = userServiceImpl.getUserByEmail(user.getEmail());
//        }
        userList.removeIf(temp -> temp.getCompany().getCid() != cid);
        System.out.println("Cid is " + cid + " and date is " + date);
        //remove all bookings that does not have the same date as the date mentioned.
        List<Bookings> bookingsList = bookingsRepository.findAll();
        System.out.println("toString1 " + bookingsList);
        Iterator<Bookings> bookingsIterator = bookingsList.iterator();
//        while (bookingsIterator.hasNext()) {
//            System.out.println();
//        }
        bookingsList.removeIf(temp -> !temp.getBDate().toString().equals(date.toString()));
        System.out.println("toString2 " + bookingsList);
        bookingsList.removeIf(temp -> temp.getStatus().equals("Confirmed"));
        System.out.println("toString3 " + bookingsList.size());
        bookingsList.forEach(temp -> System.out.println(temp.getBid()));
        //so now my bookingsList will only contain those that are pending on that date.
        bookingsIterator = bookingsList.iterator();
        int smallestBID = Integer.MAX_VALUE;
        while (bookingsIterator.hasNext()) {
            Boolean isFromSameCompany = false;
            Bookings temp = bookingsIterator.next();
            for (User u : userList) {
                if (temp.getUser().getEmail() == u.getEmail()) {
                    isFromSameCompany = true;
                }
            }
            if (!isFromSameCompany) {
                bookingsIterator.remove();
            }

        }
        //this will just contain all the bookings that are pending and from the same company.
        //have to get the smallest BID.
        bookingsIterator = bookingsList.iterator();
        System.out.println("Booking Iterator size is " + bookingsList.size());
        while (bookingsIterator.hasNext()) {
            Bookings temp = bookingsIterator.next();
            System.out.println(temp.getBid());
            if (temp.getBid() < smallestBID) {
                smallestBID = temp.getBid();
            }
        }
        //so now i will update the smallestBID's status to be Confirmed.
        System.out.println("Auto Update Bookings: " + smallestBID);
        bookingsRepository.updateBookings(smallestBID);
    }

    @Override
    public List<Bookings> getAllMyUpcomingBookings(User u) {
        LocalDateTime now = LocalDateTime.now().plusDays(1L);

        List<Bookings> bookingsList = bookingsRepository.findAllByUser(u);
        System.out.println("Upcoming " + bookingsList.size());
        Iterator<Bookings> iterator = bookingsList.iterator();
        while (iterator.hasNext()) {
            Bookings b = iterator.next();
            if (b.getBDate().isBefore(ChronoLocalDate.from(now))) {
                iterator.remove();
            }
        }
        return bookingsList;
    }

    @Override
    public Bookings getBookingsById(int id) {
        System.out.println("ID in service is " + id);
        return bookingsRepository.findById(id).orElse(null);
    }

    @Override
    public Bookings updateBookings(int id, Bookings bookings) {
        return bookingsRepository.findById(id).map(booking -> {
            booking.setBDate(bookings.getBDate());
            booking.setStatus(bookings.getStatus());
            return bookingsRepository.save(booking);
        }).orElse(null);
    }


    @Override
    public void delete(Bookings bookings) {
        bookingsRepository.delete(bookings);
    }

    /**
     * Remove a booking with the given id
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a booking will also remove all its associated reviews
     */
    @Override
    public void deleteById(int id) {
        bookingsRepository.deleteById(id);
    }

    @Override
    public boolean bookingExists(int id) {
        return bookingsRepository.existsById(id);
    }

    public int checkForDuplicateBookings(String userEmail, LocalDate date) {
        return bookingsRepository.checkForDuplicateBookings(userEmail, date);
    }

    @Override
    public Bookings save(Bookings bookings) {
        System.out.println("Before " + bookings);

        Bookings b = bookingsRepository.save(bookings);
        System.out.println("After " + b);
        return b;
    }

    @Override
    public int getBookingsCountByEmail(String email) {
        System.out.println(email);
        int x = bookingsRepository.findBookingsCountByEmail(email);
        System.out.println(x);
        return x;
    }

    @Override
    public ArrayList<Bookings> getBookingByUser(String email) {

        if (userServiceImpl.getUserByEmail(email) == null) {
            return null;

        }
        ArrayList<Bookings> toReturn = new ArrayList<>();
        for (Bookings b : bookingsRepository.findAll()) {
            if (b.getUser().getEmail().equals(email)) {
                toReturn.add(b);
            }
        }

        return toReturn;
    }
}


