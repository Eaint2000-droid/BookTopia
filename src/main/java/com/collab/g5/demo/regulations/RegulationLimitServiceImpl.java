package com.collab.g5.demo.regulations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegulationLimitServiceImpl implements RegulationLimitService {
    @Autowired
    private RegulationLimitRepository regulationLimitRepository;

    @Override
    public List<RegulationLimit> getAllRegulationLimit() {
        return regulationLimitRepository.findAll();
    }

    @Override
    public RegulationLimit getRegulationLimitByCidAndStartDate(int cid, LocalDate statDate) {
        return null;
    }

    @Override
    public RegulationLimit addRegulationLimit(RegulationLimit regulationLimit) {
        return null;
    }

    @Override
    public RegulationLimit updateRegulationLimit(int cid, RegulationLimit regulationLimit) {
        return null;
    }

    @Override
    public void deleteRegulationLimit(int id) {

    }
}
