package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingsRepository bookingsRepository;

    @Override
    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }

    @Override
    public Bookings getBookingsById(int id) {
        Bookings bookings = bookingsRepository.findById(id).orElse(null);
        if(bookings == null){
            throw new BookingNotFoundException(id);
        }
        return bookings;
    }


    @Override
    public void delete(Bookings bookings) {
        if(bookingExists(bookings.getBid())){
            bookingsRepository.delete(bookings);
        }
    }

    @Override
    public boolean bookingExists(int id) {
        return bookingsRepository.existsById(id);
    }

    @Override
    public Bookings save(Bookings bookings) {
        List<Bookings> bookingsList = bookingsRepository.findAll();
        for(Bookings b: bookingsList){
            if(b.equals(bookings)){
                throw new BookingExistsException(bookings);
            }
        }
        return bookingsRepository.save(bookings);
    }
}


