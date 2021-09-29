package com.collab.g5.demo.bookings;

import com.collab.g5.demo.bookings.BookingVetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingVetRepository extends JpaRepository<BookingVetting, BookingVettingKey> {




}
