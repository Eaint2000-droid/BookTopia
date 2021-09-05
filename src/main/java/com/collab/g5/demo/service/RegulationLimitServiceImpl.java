package com.collab.g5.demo.service;

import com.collab.g5.demo.entity.RegulationLimit;
import com.collab.g5.demo.repository.RegulationLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegulationLimitServiceImpl implements RegulationLimitService{
    @Autowired
    private RegulationLimitRepository regulationLimitRepository;

    @Override
    public List<RegulationLimit> getAllRegulationLimit() {return regulationLimitRepository.findAll();}
}
