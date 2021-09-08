package com.collab.g5.demo.regulations;

import org.hibernate.service.spi.InjectService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Regulation {

    @Id
    private LocalDate startDate;

    private LocalDate endDate;

    private int percentage;

//   foreign key
    @OneToMany(mappedBy="regulation",cascade= CascadeType.ALL)
    private List<RegulationLimit> regulationLimits;

//    public Regulation(RegulationId regulationId, int percentage) {
//        this.regulationId = regulationId;
//        this.percentage = percentage;
//    }
//
//    public LocalDate getStartDate() {
//        return regulationId.getStartDate();
//    }
//
//    public LocalDate getEndDate(){
//        return regulationId.getEndDate();
//    }


    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
