package com.collab.g5.demo.regulations;

import com.collab.g5.demo.companies.Company;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable

public class RegulationLimitKey implements Serializable {
   @Column(name="regulation_startDate")
   private LocalDate startDate;
   @Column(name="company_cid")
   private int cid;

   public RegulationLimitKey(LocalDate startDate, int cid) {
      this.startDate = startDate;
      this.cid = cid;
   }
}