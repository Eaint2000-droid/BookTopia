package com.collab.g5.demo.regulations;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

public class RegulationId implements Serializable {

    private LocalDate startDate;
    private LocalDate endDate;
    public RegulationId(LocalDate startDate, LocalDate endDate){
        this.startDate=startDate;
        this.endDate=endDate;
    }
}
