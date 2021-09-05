package com.collab.g5.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
//@IdClass(com.collab.g5.demo.entity.User.class)
public class BookingVetting {
    @Id
    private int bid;
    private String userEmail;
    private String bookingResult;
    private String comment;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingVetting that = (BookingVetting) o;
        return bid == that.bid && userEmail.equals(that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, userEmail);
    }
}
