package com.collab.g5.demo.bookings;

import com.collab.g5.demo.bookings.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings,Integer> {

}
