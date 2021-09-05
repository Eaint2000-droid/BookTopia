package com.collab.g5.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class RegulationLimit {
    @Id
    private LocalDate startdate;
    private LocalDate endDate;
    private int cid;
    private int dailyLimit;

    public RegulationLimit(LocalDate startdate, LocalDate endDate, int cid, int dailyLimit) {
        this.startdate = startdate;
        this.endDate = endDate;
        this.cid = cid;
        this.dailyLimit = dailyLimit;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }
}
