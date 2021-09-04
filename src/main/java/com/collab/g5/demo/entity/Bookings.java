package com.collab.g5.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table(name ="bookings")
public class Bookings {
    @Id
    private int bid;

    private String userEmail;
    private LocalDate bDate;
    private String status;

    public Bookings(int bid, String email, LocalDate bDate, String status) {
        this.bid = bid;
        this.userEmail = email;
        this.bDate = bDate;
        this.status = status;
    }

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

    public LocalDate getbDate() {
        return bDate;
    }

    public void setbDate(LocalDate bDate) {
        this.bDate = bDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
