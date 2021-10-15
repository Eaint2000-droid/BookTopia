package com.collab.g5.demo.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingVetImpl implements BookingVetService {

    @Autowired
    private BookingVetRepository bookingVetRepository;

    @Override
    public BookingVetting save(BookingVetting bookingVetting) {
        return bookingVetRepository.save(bookingVetting);
    }

    @Override
    public List<BookingVetting> getAllBookingVetting() {
        return bookingVetRepository.findAll();
    }

    @Override
    public BookingVetting getById(BookingVettingKey id) {
        return bookingVetRepository.getById(id);
    }

    @Override
    public BookingVetting updateBookings(BookingVettingKey id, BookingVetting bookings) {
        return bookingVetRepository.findById(id).map(booking -> {
            booking.setBookingResult(bookings.getBookingResult());
            return bookingVetRepository.save(booking);
        }).orElse(null);
    }

    @Override
    public void delete(BookingVetting bookingVetting) {
        bookingVetRepository.delete(bookingVetting);
    }

    @Override
    public void deleteById(BookingVettingKey id) {
        bookingVetRepository.deleteById(id);
    }

    @Override
    public boolean bookingVettingExists(BookingVettingKey id) {
        return bookingVetRepository.existsById(id);
    }
}
