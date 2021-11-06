package com.collab.g5.demo.regulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class RegulationServiceImpl implements RegulationService {
    private RegulationRepository regulationRepository;

    @Autowired
    public RegulationServiceImpl (RegulationRepository regulationRepository){
        this.regulationRepository = regulationRepository;
    }

    @Override
    public Regulation save(Regulation newRegulation) {
        return regulationRepository.save(newRegulation);
    }

    @Override
    public List<Regulation> getAllRegulation() {
        return regulationRepository.findAll();
    }

    @Override
    public List<List<String>> getAllRegulationWithLimit(String userEmail) {
        return regulationRepository.findAllRegulationWithLimit(userEmail);
    }

    @Override
    public Regulation getRegulationById(LocalDate startDate) {
        return regulationRepository.findById(startDate).map(regulation ->{
            return regulation;
        }).orElse(null);
    }

    @Override
    public Regulation updateRegulation(LocalDate startDate, Regulation newRegulation) {
        return regulationRepository.findById(startDate).map(regulation -> {
            regulation.setStartDate(newRegulation.getStartDate());
            regulation.setEndDate(newRegulation.getEndDate());
            regulation.setPercentage(newRegulation.getPercentage());
            return regulationRepository.save(regulation);
        }).orElse(null);
    }

    @Override
    public void deleteRegulationById(LocalDate startDate) {
        regulationRepository.deleteById(startDate);
    }
}
