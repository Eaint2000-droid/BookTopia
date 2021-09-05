package com.collab.g5.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nID;

    private LocalDate date;
    private String title;
    private String content;
    private int cid;
    private String userEmail;

    public News(int nID, LocalDate date, String title, String content, int cid, String userEmail) {
        this.nID = nID;
        this.date = date;
        this.title = title;
        this.content = content;
        this.cid = cid;
        this.userEmail = userEmail;
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
