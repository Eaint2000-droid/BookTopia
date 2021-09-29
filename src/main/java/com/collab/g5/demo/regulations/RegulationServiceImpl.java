package com.collab.g5.demo.regulations;

import com.collab.g5.demo.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegulationServiceImpl implements RegulationService {
    @Autowired
    private RegulationRepository regulationRepository;

    @Override
    public Regulation save(Regulation newRegulation) {
        return regulationRepository.save(newRegulation);
    }

    @Override
    public List<Regulation> getAllRegulation() {return regulationRepository.findAll();}

    @Override
    public Regulation getRegulationById(LocalDate startDate) {
        return regulationRepository.getById(startDate);
    }

    @Override
    public Regulation updateRegulation(LocalDate startDate, Regulation newRegulation) {
        Regulation tempRegulation = regulationRepository.findById(startDate)
                .orElseThrow(() -> new IllegalStateException("Regulation with Start Date " + startDate + " does not exist"));
        tempRegulation.setPercentage(newRegulation.getPercentage());
        return tempRegulation;
    }

    @Override
    public void delete(Regulation regulation) {
        regulationRepository.delete(regulation);
    }

    @Override
    public void deleteById(LocalDate startDate) {
        regulationRepository.deleteById(startDate);
    }
}
