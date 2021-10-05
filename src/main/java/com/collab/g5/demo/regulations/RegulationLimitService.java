package com.collab.g5.demo.regulations;

import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface RegulationLimitService {
    //CREATE
    RegulationLimit save(RegulationLimit newRegulation);

    //READ
    List<RegulationLimit> getAllRegulationLimit();

    RegulationLimit getRegulationLimitById(LocalDate dateID,int cid);

    //UPDATE
    RegulationLimit updateRegulationLimit(int cid, Regulation newRegulation);

    //DELETE
    void delete(RegulationLimit regulation);

    void deleteById(RegulationLimitKey regulationLimitKey);


}
