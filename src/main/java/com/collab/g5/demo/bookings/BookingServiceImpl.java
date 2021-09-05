package com.collab.g5.demo.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingsRepository bookingsRepository;

    @Override
    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }
}


