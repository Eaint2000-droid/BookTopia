package com.collab.g5.demo.bookings;

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


