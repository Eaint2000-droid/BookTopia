package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BookingVetting {
    @EmbeddedId
    BookingVettingKey id;

    @ManyToOne
    @MapsId("bookingsId")
    @JoinColumn(name="bookings_bid")
    private Bookings booking;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name="user_userEmail")
    private User user;

    private String bookingResult;
    private String comment;

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
