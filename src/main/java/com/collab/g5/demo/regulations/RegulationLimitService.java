package com.collab.g5.demo.regulations;

import java.time.LocalDate;
import java.util.List;

public interface RegulationLimitService {
    //CREATE
    RegulationLimit save(RegulationLimit newRegulation);

    //READ
    List<RegulationLimit> getAllRegulationLimit();

    RegulationLimit getRegulationById(LocalDate dateID);

    //UPDATE
    RegulationLimit updateRegulation(int cid, Regulation newRegulation);

    //DELETE
    void delete(RegulationLimit regulation);

    void deleteById(RegulationLimitKey regulationLimitKey);
}
