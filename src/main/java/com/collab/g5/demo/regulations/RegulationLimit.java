package com.collab.g5.demo.regulations;

import com.collab.g5.demo.companies.Company;

import javax.persistence.*;
import java.time.LocalDate;

@Entity

public class RegulationLimit {
    @EmbeddedId
    RegulationLimitKey regulationLimitKey;

    @ManyToOne
    @MapsId("regulationId")
    @JoinColumn(name="regulation_startDate")
    @JoinColumn(name = "regulation_startDate", foreignKey = @ForeignKey(name = "fk1_regulationLimit"))
    private Regulation regulation;

    @ManyToOne
    @MapsId("companyId")
    @JoinColumn(name="company_cid")
    @JoinColumn(name = "company_cid", foreignKey = @ForeignKey(name = "fk2_regulationLimit"))
    private Company company;

    private int dailyLimit;


//    public RegulationLimit(Regulation regulation, Company company) {
//        this.regulation = regulation;
//        this.company = company;
//    }
//
//    public void setRegulation(Regulation regulation) {
//        this.regulation = regulation;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }
//
//
//    public Regulation getRegulation() {
//        return regulation;
//    }
//
//    public Company getCompany() {
//        return company;
//    }

//    public LocalDate getStartDate() {
//        return regulation.getStartDate();
//    }
//
//    public LocalDate getEndDate(){
//        return regulation.getEndDate();
//    }

    public long getCid(){
        return company.getCid();
    }
    public int getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }
}
