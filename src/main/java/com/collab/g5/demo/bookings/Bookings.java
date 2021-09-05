package com.collab.g5.demo.bookings;

import com.collab.g5.demo.users.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "com/collab/g5/demo/bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bid;

    @ManyToOne
    @JoinColumn(name="user_userEmail")
    private User user;

    private LocalDate bDate;
    private String status;

//    foreign key
    @OneToMany(mappedBy="booking", cascade = CascadeType.ALL)
    private List<BookingVetting> bookingVettings;


    public Bookings(int bid, User user, LocalDate bDate, String status) {
        this.bid = bid;
        this.user = user;
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
        return user.getUserEmail();
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
