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
    public RegulationLimit save(RegulationLimit newRegulation) {
        return null;
    }

    @Override
    public List<RegulationLimit> getAllRegulation() {
        return null;
    }

    @Override
    public RegulationLimit getRegulationById(LocalDate dateID) {
        return null;
    }

    @Override
    public RegulationLimit updateRegulation(int cid, Regulation newRegulation) {
        return null;
    }

    @Override
    public void delete(RegulationLimit regulation) {

    }

    @Override
    public void deleteById(RegulationLimitKey regulationLimitKey) {

    }
}
