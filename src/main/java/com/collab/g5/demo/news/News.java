package com.collab.g5.demo.news;

import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.users.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nID;

    private LocalDate date;
    private String title;
    private String content;

    //foreign keys
    @ManyToOne
    @JoinColumn(name="company_cid")
    private Company company;

    @ManyToOne
    @JoinColumn(name="user_userEmail")
    private User user;



    public News(int nID, LocalDate date, String title, String content, Company company, User user) {
        this.nID = nID;
        this.date = date;
        this.title = title;
        this.content = content;
        this.company = company;
        this.user = user;
    }

    public int getnID() {
        return nID;
    }

    public void setnID(int nID) {
        this.nID = nID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getnTitle() {
        return title;
    }

    public void setnTitle(String nTitle) {
        this.title = nTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCid() {
        return company.getCid();
    }

    public String getUserEmail() {
        return user.getUserEmail();
    }

}
