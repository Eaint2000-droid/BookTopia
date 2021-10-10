package com.collab.g5.demo.bookings;

import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingsRepository bookingsRepository;

    @Override
    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }

    @Override
    public Bookings getBookingsById(int id) {
        Bookings bookings = bookingsRepository.findById(id).orElse(null);
        if (bookings == null) {
            throw new BookingNotFoundException(id);
        }
        return bookings;
    }

    @Override
    public Bookings updateBookings(int id, Bookings bookings) {
        return null;
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
        List<Bookings> bookingsList = bookingsRepository.findAll();
        for (Bookings b : bookingsList) {
            if (b.equals(bookings)) {
                return null;
//                throw new BookingExistsException(bookings);
            }
        }
        return bookingsRepository.save(bookings);
    }
}


