package com.collab.g5.demo.regulations;

import com.collab.g5.demo.companies.Company;

import java.time.LocalDate;
import java.util.List;

public interface RegulationService {
    //CREATE
    Regulation save(Regulation newRegulation);

    //READ
    List<Regulation> getAllRegulation();

    Regulation getRegulationById(LocalDate dateID);

    //UPDATE
    Regulation updateRegulation(int cid, Regulation newRegulation);

    //DELETE
    void delete(Regulation regulation);

    void deleteById(LocalDate dateID);
}
