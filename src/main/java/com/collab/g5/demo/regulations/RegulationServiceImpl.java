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
}
