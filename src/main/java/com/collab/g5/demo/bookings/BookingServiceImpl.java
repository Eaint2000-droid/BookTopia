package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Iterator;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingsRepository bookingsRepository;


    //for hr
    @Override
    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }


//    //for emp how do i make sure it returns my ownstuff only
//    @Override
//    public List<Bookings> getAllMyBookings() {
//        return bookingsRepository.findAll();
//
//    }
    
    public List<Bookings> getAllMyPastBookings(User u) {
        System.out.println("getAllMyPastBookings: " + u);
        LocalDateTime now = LocalDateTime.now().minusDays(1L);
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

    public List<Bookings> getAllMyUpcomingBookings(User u) {
        LocalDateTime now = LocalDateTime.now();

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

    @Override
    public Bookings save(Bookings bookings) {
        return bookingsRepository.save(bookings);
    }

    public int getBookingsCountByEmail(String email) {
        return bookingsRepository.findBookingsCountByEmail(email);
    }
}


