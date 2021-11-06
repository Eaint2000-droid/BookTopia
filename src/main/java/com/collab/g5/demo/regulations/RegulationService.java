package com.collab.g5.demo.regulations;

import java.time.LocalDate;
import java.util.List;

public interface RegulationService {
    //CREATE
    Regulation save(Regulation newRegulation);

    //READ
    List<Regulation> getAllRegulation();

    List<List<String>> getAllRegulationWithLimit(String userEmail);

    Regulation getRegulationById(LocalDate startDate);

    //UPDATE
    Regulation updateRegulation(LocalDate startDate, Regulation newRegulation);

    //DELETE
    /**
     * Method's signature: do not return a value for delete operation
     * @param startDate
     */
    void deleteRegulationById(LocalDate startDate);
}
