package com.collab.g5.demo.users;

import com.collab.g5.demo.bookings.BookingVetting;
import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.news.News;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="userEmail",nullable=false)
    private String userEmail;
    private boolean HR;
    private String password;
    private String fname;
    private String lname;
    private String dept;
    private String role;

    @ManyToOne
    @JoinColumn(name="Company_cid")
    private Company company;

//    foreign keys

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<News> newsList;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<BookingVetting> bookingVetting;

    @OneToMany(mappedBy="user",cascade= CascadeType.ALL)
    private List<Bookings> bookings;

    public User() {
    }


    public User(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getFname() {
        return fname;
    }

    public Boolean getHR() {
        return HR;
    }

    public String getLname() {
        return lname;
    }

    public String getDept() {
        return dept;
    }

    public String getRole() {
        return role;
    }

    public Long getCid() {
        return company.getCid();
    }

    public Company getCompany() {
        return company;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public List<Bookings> getBookings() {
        return bookings;
    }

    public List<BookingVetting> getBookingVetting() {
        return bookingVetting;
    }

    public boolean isHR() {return HR;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public void setHR(Boolean HR) {this.HR = HR;}



}
