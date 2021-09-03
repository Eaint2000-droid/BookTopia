package com.collab.g5.demo.entity;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity

public class User {

    @Id
    private String userEmail;
    private String password;
    private String fname;
    private String lname;
    private String dept;
    private String role;
    private Long cid;

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
        return cid;
    }

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

    public void setCid(Long cid) {
        this.cid = cid;
    }
}
