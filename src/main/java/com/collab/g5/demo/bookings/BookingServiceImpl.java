package com.collab.g5.demo.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

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


    public List<Bookings> getAllMyPastBookings() {
        LocalDateTime now = LocalDateTime.now();
        List<Bookings> bookingsList = new ArrayList<>(bookingsRepository.findAll());
        for (int i =0; i<bookingsList.size();i++){
            if(bookingsList.get(i).getBDate().isAfter(ChronoLocalDate.from(now))){
                bookingsList.remove(i);
            }
        }
        return bookingsList;
    }

    public List<Bookings> getAllMyUpcomingBookings() {
        LocalDateTime now = LocalDateTime.now();
        List<Bookings> bookingsList = new ArrayList<>(bookingsRepository.findAll());
        for (int i =0; i<bookingsList.size();i++){
            if(!bookingsList.get(i).getBDate().isAfter(ChronoLocalDate.from(now))){
                bookingsList.remove(i);
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
}


