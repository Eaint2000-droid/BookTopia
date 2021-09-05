package com.collab.g5.demo.bookings;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable

public class BookingVettingKey implements Serializable {
    @Column(name="bookings_bid")
    Long bid;

    @Column(name="user_userEmail")
    String userEmail;

    public BookingVettingKey(Long bid, String userEmail) {
        this.bid = bid;
        this.userEmail = userEmail;
    }
}