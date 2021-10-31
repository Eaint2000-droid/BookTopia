package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings,Integer> {

    @Query("SELECT count(*) FROM Bookings b WHERE b.user.email = ?1 and MONTH(b.bDate)=MONTH(now())")
    int findBookingsCountByEmail(String email);


    List<Bookings> findAllByUser(User user);
}
