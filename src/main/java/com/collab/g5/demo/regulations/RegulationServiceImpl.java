package com.collab.g5.demo.regulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegulationServiceImpl implements RegulationService {
    @Autowired
    private RegulationRepository regulationRepository;

    @Override
    public List<Regulation> getAllRegulation() {return regulationRepository.findAll();}

    @Override
    public Regulation updateRegulation(int cid, Regulation regulation) {
        return null;
    }

    @Override
    public Regulation save(Regulation newRegulation) {
        return regulationRepository.save(newRegulation);
    }

    @Override
    public Regulation getRegulationById(LocalDate dateID) {
        return regulationRepository.getById(dateID);
    }

    @Override
    public void delete(Regulation regulation) {
        regulationRepository.delete(regulation);
    }

    @Override
    public void deleteById(LocalDate dateID) {
        regulationRepository.deleteById(dateID);
    }
}
