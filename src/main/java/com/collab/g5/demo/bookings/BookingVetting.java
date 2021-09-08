package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BookingVetting {
    @EmbeddedId
    BookingVettingKey bookingVettingKey;

    private String bookingResult;
    private String comment;

    @ManyToOne
    @MapsId("useremail")
    @JoinColumn(name = "user_useremail", foreignKey = @ForeignKey(name = "fk1_bookingVetting"))
    private User user;

    @Override
    public String toString() {
        return "BookingVetting{" +
                bookingVettingKey.getBid()+
                ", user"+ bookingVettingKey.getUseremail()+
                ", bookingResult='" + bookingResult + '\'' +
                ", comment='" + comment + '\'' + '}';
    }

    //    foreign key

    @ManyToOne
    @MapsId("bid")
    @JoinColumn(name = "booking_bid", foreignKey = @ForeignKey(name = "fk2_bookingVetting"))
    private Bookings booking;
}
