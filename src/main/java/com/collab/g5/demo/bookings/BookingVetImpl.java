package com.collab.g5.demo.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingVetImpl implements BookingVetService {

    @Autowired
    private BookingVetRepository bookingVetRepository;

    @Override
    public List<BookingVetting> getAllBookings() {
        return bookingVetRepository.findAll();
    }
}
