package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BookingVetting {
    @EmbeddedId
    BookingVettingKey id;

    private String bookingResult;
    private String comment;

    @ManyToOne
    @MapsId("useremail")
    @JoinColumn(name = "useremail", foreignKey = @ForeignKey(name = "fk1_bookingVetting"))
    private User user;

//    foreign key

    @ManyToOne
    @MapsId("bid")
    @JoinColumn(name = "bid", foreignKey = @ForeignKey(name = "fk2_bookingVetting"))
    private Bookings booking;
}
