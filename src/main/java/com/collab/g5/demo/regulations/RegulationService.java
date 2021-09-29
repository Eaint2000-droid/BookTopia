package com.collab.g5.demo.regulations;

import java.time.LocalDate;
import java.util.List;

public interface RegulationService {
    //CREATE
    Regulation save(Regulation newRegulation);

    //READ
    List<Regulation> getAllRegulation();

    Regulation getRegulationById(LocalDate startDate);

    //UPDATE
    Regulation updateRegulation(LocalDate startDate, Regulation newRegulation);

    //DELETE
    void delete(Regulation regulation);

    void deleteById(LocalDate startDate);
}
