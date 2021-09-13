package com.collab.g5.demo.regulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegulationServiceImpl implements RegulationService {
    @Autowired
    private RegulationRepository regulationRepository;

    @Override
    public List<Regulation> getAllRegulation() {return regulationRepository.findAll();}

    @Override
    public Regulation getRegulationBy(int id) {
        return null;
    }

    @Override
    public Regulation addRegulation(RegulationLimit regulation) {
        return null;
    }

    @Override
    public Regulation addRegulation(Regulation regulation) {
        return null;
    }

    @Override
    public Regulation updateRegulation(int cid, Regulation regulation) {
        return null;
    }

    @Override
    public void deleteRegulation(int id) {

    }
}
