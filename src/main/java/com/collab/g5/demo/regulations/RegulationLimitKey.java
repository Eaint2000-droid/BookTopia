package com.collab.g5.demo.regulations;

import com.collab.g5.demo.companies.Company;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class RegulationLimitKey implements Serializable {

   Regulation regulation;

   @Column(name="company_cid")
   Long cid;

   public RegulationLimitKey(Regulation regulation, Long cid) {
      this.regulation = regulation;
      this.cid = cid;
   }
}