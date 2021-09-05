package com.collab.g5.demo.regulations;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@IdClass(RegulationId.class)
public class Regulation {
    @Id
    private LocalDate startDate;
    @Id
    private LocalDate endDate;
    private int percentage;

//   foreign key
    @OneToMany(mappedBy="regulation",cascade= CascadeType.ALL)
    private List<RegulationLimit> regulationLimits;


    public Regulation(LocalDate startdate, LocalDate endDate, int percentage) {
        this.startDate = startdate;
        this.endDate = endDate;
        this.percentage = percentage;
    }

    public LocalDate getStartdate() {
        return startDate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startDate = startdate;
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
