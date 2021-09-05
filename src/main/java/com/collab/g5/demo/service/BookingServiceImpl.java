package com.collab.g5.demo.service;

import com.collab.g5.demo.entity.Bookings;
import com.collab.g5.demo.repository.BookingsRepository;
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


