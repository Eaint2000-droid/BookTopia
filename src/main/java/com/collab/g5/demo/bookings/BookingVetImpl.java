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
    public BookingVetting getBookingVettingById(BookingVettingKey id) {
        return bookingVetRepository.getById(id);
    }

    @Override
    public BookingVetting updateBookings(int id, BookingVetting bookings) {
        return null;
    }

    @Override
    public void delete(BookingVetting bookingVetting) {
        bookingVetRepository.delete(bookingVetting);
    }

    @Override
    //TODO
    //Got to figure this one out.
    public void deleteById(BookingVettingKey id) {
        bookingVetRepository.deleteById(id);
    }

    @Override
    //TODO
    //Got to figure this one out.
    public boolean bookingVettingExists(BookingVettingKey id) {
        return bookingVetRepository.existsById(id);
    }
}
