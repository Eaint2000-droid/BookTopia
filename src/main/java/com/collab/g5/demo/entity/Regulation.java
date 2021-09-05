package com.collab.g5.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Regulation {
    @Id
    private LocalDate startdate;
    private LocalDate endDate;
    private int percentage;

    public Regulation(LocalDate startdate, LocalDate endDate, int percentage) {
        this.startdate = startdate;
        this.endDate = endDate;
        this.percentage = percentage;
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

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
