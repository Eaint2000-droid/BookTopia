package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * We only need this interface declaration
 * Spring will automatically generate an implementation of the repo
 *
 * JpaRepository provides more features by extending PagingAndSortingRepository, which in turn extends CrudRepository
 */
@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

    @Query("SELECT count(b) FROM Bookings b WHERE b.user.email = ?1 and MONTH(b.bDate)=MONTH(now()) and b.status='completed'")
    int findBookingsCountByEmail(String email);

    //    @Query("SELECT count(b) FROM Bookings b WHERE b.bDate = ?1 and b.")
    @Query(value = "select count(*) from company c inner join \n" +
            "( select * from bookings b inner join user u where b.user_useremail = u.email ) as bookinguser using (cid) \n" +
            "where c.cid = ?1 and bookinguser.status = \"completed\" and bookinguser.b_date = ?2", nativeQuery = true)
    int getBookingsCountByDate(int cid, LocalDate bDate);

    List<Bookings> findAllByUser(User user);

    @Query("SELECT b FROM Bookings b WHERE b.user.email = ?1")
    List<Bookings> findBookingsByEmail(String email);
}
