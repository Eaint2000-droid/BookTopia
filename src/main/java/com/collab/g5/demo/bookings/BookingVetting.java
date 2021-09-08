package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BookingVetting {
    @EmbeddedId
    BookingVettingKey id;

    private String bookingResult;
    private String comment;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "user_userEmail", foreignKey = @ForeignKey(name = "fk1_bookingVetting"))
    private User user;

//    foreign key

    @ManyToOne
    @MapsId("bookingsId")
    @JoinColumn(name = "bookings_bid", foreignKey = @ForeignKey(name = "fk2_bookingVetting"))
    private Bookings booking;





    public void setId(BookingVettingKey id) {
        this.id = id;
    }

    public BookingVettingKey getId() {
        return id;
    }

    public int getBid() {
        return booking.getBid();
    }


    public String getUserEmail() {
        return user.getUserEmail();
    }


    public String getBookingResult() {
        return bookingResult;
    }

    public void setBookingResult(String bookingResult) {
        this.bookingResult = bookingResult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
